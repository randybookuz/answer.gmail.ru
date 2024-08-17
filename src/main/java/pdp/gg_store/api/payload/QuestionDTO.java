package pdp.gg_store.api.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import pdp.gg_store.api.payload.TagsDTO;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Value
public class QuestionDTO implements Serializable {
    UUID id;
    @NotNull(message = "title It can't be null")
    @Size(message = "wrong title size,must be 10-100", min = 10, max = 100)
    @NotBlank(message = "title It can't be blank")
    String title;
    @Size(message = "wrong content size must be 30-1000", min = 30, max = 1000)
    @NotEmpty(message = "content can't be null")
    @NotBlank(message = "content  can't be blank")
    String content;
    UUID categoryId;

    @Size(max = 10)
    List<String> tags;
}