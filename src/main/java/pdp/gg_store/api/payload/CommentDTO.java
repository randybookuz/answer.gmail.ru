package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import pdp.gg_store.model.Comment;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Comment}
 */
@Value
public class CommentDTO implements Serializable {
    UUID id;
    UUID questionId;
    UUID answerId;
    @NotBlank(message = "content cannot be blank")
    @NotNull(message = "content  cannot be null")
    @Size(message = "wrong content length", min = 3, max = 300)
    String content;
}