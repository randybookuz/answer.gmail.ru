package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;
import pdp.gg_store.model.Answer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * DTO for {@link Answer}
 */
@Value
public class AnswerResponseDTO implements Serializable {
    UUID id;
    UUID authorId;
    Timestamp createdAt;
    Timestamp updatedAt;

    @Size(message = "wrong content size must be 30-1000", min = 30, max = 1000)
    @NotEmpty(message = "content can't be null")
    @NotBlank(message = "content  can't be blank")
    String content;
    UUID questionId;

    boolean deleted;
    Integer rating;
    boolean accepted;
    boolean edited;
}