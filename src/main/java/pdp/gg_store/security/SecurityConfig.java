package pdp.gg_store.security;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;



import pdp.gg_store.service.interfaces.UserService;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "App api description",
                version = "${api.version}",
                contact = @Contact(
                        name = "Akbarxon Gafurov", email = "akbarxongafurov0211@gmail.com", url = "https://github.com/randybookk"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"),
                description = "My project description"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring 6 Wiki Documentation", url = "https://springshop.wiki.github.org/docs"
        ),
        servers = {
                @Server(
                        url = "http://localhost:80",
                        description = "Production-Server"
                ),
                @Server(
                        url = "http://localhost:8080",
                        description = "Test-Server"
                )
        }
)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private  final  JWTFilter jwtFilter;
    private  final UserService userService;



    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws  Exception {
       httpSecurity
             .authorizeHttpRequests(
                    conf -> conf
                        .requestMatchers("api/v1/auth/**")
                        .permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-resources/**","/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("api/v1/endpoint", "api/v1/admin/**").hasRole("Admin")
                        .anyRequest().authenticated())

             .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authenticationProvider(authenticationProvider())
             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
             .csrf(AbstractHttpConfigurer::disable)
             .cors(conf -> conf.configurationSource(req -> {
                    var config = new CorsConfiguration();

                    config.setAllowedOriginPatterns(List.of("*"));// какой домен может обращаться
                    config.setAllowedMethods(List.of("GET", "PUT", "POST", "DELETE"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);//куки
                    return config;
                }))
             ;
                return  httpSecurity.build();

    }

@Bean
public PasswordEncoder passwordEncoder(){
    return  new BCryptPasswordEncoder();
}

@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userService.userDetails());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return  authenticationProvider;
}

@Bean
public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
    return  configuration.getAuthenticationManager();
}
}