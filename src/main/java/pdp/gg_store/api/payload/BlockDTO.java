package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import pdp.gg_store.model.Block;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Block}
 */
@Value
public class BlockDTO implements Serializable {
    UUID id;
    UUID userID;
    @Size(message = "wrong reason size must be 5-400", min = 5, max = 400)
    @NotNull(message = "reason can't be null")
    @NotBlank(message = "reason  can't be blank")
    String reason;
}