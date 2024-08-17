package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.SignInDTO;
import pdp.gg_store.api.payload.SignUpDTO;
import pdp.gg_store.api.payload.TokenDTO;

public interface AuthSerice  {
    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    ApiResult<TokenDTO> signUp(SignUpDTO signUpDTO);

}
