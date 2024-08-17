package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import pdp.gg_store.model.Category;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Category}
 */
@Value
public class CategoryDTO implements Serializable {
    UUID id;
    @NotBlank(message = "category decription cannot be null")
    @NotNull(message = "category name cannot be null")
    @Size(message = "wrong category length", min = 3, max = 50)
    String name;
    @NotBlank(message = " description cannot be blank")
    @NotNull(message = "description cannot be null")
    @Size(message = "wrong description length", min = 3, max = 300)
    String description;
    UUID parentCategoryId;

}