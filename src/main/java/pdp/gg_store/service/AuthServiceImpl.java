package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.exceptions.RestException;
import pdp.gg_store.api.payload.*;
import pdp.gg_store.model.DAO.RoleRepository;
import pdp.gg_store.model.DAO.UserRepository;
import pdp.gg_store.model.Role;
import pdp.gg_store.model.User;
import pdp.gg_store.model.UserSettings;
import pdp.gg_store.model.enums.LanguageEnum;
import pdp.gg_store.model.enums.RoleEnum;
import pdp.gg_store.service.interfaces.AuthSerice;

import pdp.gg_store.service.interfaces.JWTService;
import pdp.gg_store.service.interfaces.UserService;

import java.sql.Timestamp;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthSerice {
    private  final UserService userService;
    private  final JWTService jwtService;
    private  final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;
    private  final RoleRepository roleRepository;
    private  final UserRepository userRepository;


    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsername(),signInDTO.getPassword()));
            var user=userService.userDetails().loadUserByUsername(signInDTO.getUsername());
            var jwt=jwtService.generateToken(user);
            return  ApiResult.success(new TokenDTO(jwt));
        } catch (AuthenticationException e) {
        e.printStackTrace();
        return ApiResult.error(e.getMessage());
    }

    }

    @Override
    public ApiResult<TokenDTO> signUp(SignUpDTO signUpDto){
      try{
          if (userRepository.existByUsername(signUpDto.getUsername()))
            throw RestException.alreadyExist("username");
          User user=null;
         user= new User(
                signUpDto.getUsername(),
                passwordEncoder.encode(signUpDto.getPassword()),
                signUpDto.getEmail(),
                getUserRole(),
                signUpDto.getFirstName(),
                signUpDto.getLastName(),
                signUpDto.getPhoneNumber(),
                "/static/img/avatarDefault",
                new Timestamp(System.currentTimeMillis()),
                true,
                false,
                new ArrayList<>(),
                new ArrayList<>(),
                1,
                new UserSettings(user, LanguageEnum.RU,true,true,true)

        );
                userService.create(user);
                var jwt=jwtService.generateToken(user);
        return  ApiResult.success(new TokenDTO(jwt));
    } catch (AuthenticationException e) {
        e.printStackTrace();
        return ApiResult.error(e.getMessage());
    }

    }





    private  Role getUserRole(){
        Optional<Role> optionalRole=roleRepository.findByType(RoleEnum.USER);
        return optionalRole.orElseThrow(NoSuchElementException::new);
    }


}
