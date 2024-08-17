package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;
import pdp.gg_store.model.Comment;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO for {@link Comment}
 */
@Value
public class CommentResponseDTO implements Serializable {
    UUID authorId;
    Timestamp createdAt;
    Timestamp updatedAt;
    UUID id;
    boolean deleted;
    UUID questionId;

    UUID answerId;

    @Size(message = "wrong content size must be 30-1000", min = 30, max = 1000)
    @NotEmpty(message = "content can't be null")
    @NotBlank(message = "content  can't be blank")
    String content;
    boolean edited;
}