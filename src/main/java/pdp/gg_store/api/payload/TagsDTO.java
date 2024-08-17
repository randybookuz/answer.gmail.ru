package pdp.gg_store.api.payload;

import jakarta.validation.constraints.*;
import lombok.Value;



import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link TagsDTO}
 */
@Value
public class TagsDTO implements Serializable {
    UUID id;
    @Size(message = "wrong name size must be 30-1000", min = 30, max = 1000)
    @NotEmpty(message = "name can't be null")
    @NotBlank(message = "namet  can't be blank")
    @Pattern(message = "the tag must consist of one word", regexp = "^\\w+$")
    String name;

}