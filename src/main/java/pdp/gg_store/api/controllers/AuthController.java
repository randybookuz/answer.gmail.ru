package pdp.gg_store.api.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.SignInDTO;
import pdp.gg_store.api.payload.SignUpDTO;
import pdp.gg_store.api.payload.TokenDTO;
import pdp.gg_store.api.utils.AppConstants;

import pdp.gg_store.service.AuthServiceImpl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/auth")
@Tag(name="Authentication")
public class AuthController {
    private  final AuthServiceImpl authService;



    @Operation(summary="sign-up")
    @PostMapping("/sign-up")
    public ApiResult<TokenDTO>  signUp(@RequestBody @Valid SignUpDTO signUpDto) {
        return  authService.signUp(signUpDto);
    }


    @Operation(summary="sign-in")
    @PostMapping("/sign-in")
    public ApiResult<TokenDTO> signIn(@RequestBody @Valid SignInDTO signInDto){
        log.info("Request -> AuthController -> signIn -> params -> {}", signInDto);
        return authService.signIn(signInDto);

    }

}
