package pdp.gg_store.api.payload;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link pdp.gg_store.model.User}
 */
@Value
public class SignUpDTO implements Serializable {
    @Size(message = "username should be between 5-40 symbols", min = 5, max = 40)
    @NotBlank(message = "username cannot be emply")
    String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @NotBlank(message = "pasword cannot be emply")

    String password;
    @Size(message = "email should be between 5-40 symbols", min = 5, max = 40)
    @Email(message = " wrong email", regexp = "^(?=[^@]+@[^@]+\\.[^@]+)\\S+$")
    @NotBlank(message = "email cannot be emply")
    String email;
    @Size(message = "firstname should be betwwen 3-30 symbols", min = 3, max = 30)
    @NotBlank(message = "firstname cannot be emply")
    String firstName;
    String lastName;
    @Pattern(message = "wrong phoneNumber", regexp = "^\\+998\\d{9}$")
    @NotBlank(message = "phoneNumber cannot be emply")
    String phoneNumber;
}