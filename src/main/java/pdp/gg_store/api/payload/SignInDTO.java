package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link pdp.gg_store.model.User}
 */
@Value
public class SignInDTO implements Serializable {
    @Size(message = "username should be between 5-40 symbols", min = 5, max = 40)
    @NotBlank(message = "username cannot be emply")
    String username;
    @Size(message = "password should be between 5-40 symbols", min = 6, max = 60)
    @NotBlank(message = "pasword cannot be emply")
    String password;
}