package pdp.gg_store.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pdp.gg_store.model.User;
import pdp.gg_store.service.interfaces.JWTService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    @Value("${token.signing.key}")
    private  String jwtSigningKey;


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    private <T>T extractClaim(String token, Function<Claims,T> claimsTFunction) {
        final  Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return  Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String,Object>claims=new HashMap<>();
        if(userDetails instanceof User user){
            claims.put("id",user.getId());
            claims.put("email",user.getEmail());
            claims.put("role",user.getRole());
        }
        return generateToken(claims,userDetails);
    }
private  String generateToken(Map<String,Object>map,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(map)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+24_400_300))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

}

    private SecretKey getSigningKey() {
        byte[] bytes= Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final  String userName=extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration);
    }


}
