package pdp.gg_store.api.controllers;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.TagsDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.TagsMapper;
import pdp.gg_store.model.Tags;
import pdp.gg_store.service.interfaces.TagsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/tags")
@Tag(name="Tags")
public class TagsController {

    private final TagsService tagsService;
    private final TagsMapper mapper;


    @Operation(summary="getAllTagss(")
    @GetMapping
    public ResponseEntity<List<TagsDTO>> getAllTagss() {
        ApiResult<List<Tags>> allTags = tagsService.getAllTags();
        if (allTags.isSuccess()) {
            List<TagsDTO> tagsDTOS = mapper.toDTOs(allTags.getData());
            return ResponseEntity.ok(tagsDTOS);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="getQuetionsByTagName")
    @GetMapping("/{name}")
    public ResponseEntity<List<UUID>> getQuetionsByTagName(@PathVariable String name) {

        ApiResult<List<UUID>> questionsByTagName = tagsService.getQuestionsByTagName(name);
        if (questionsByTagName.isSuccess()) {
            return ResponseEntity.ok(questionsByTagName.getData());

        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="createTags")
    @PostMapping
    public ResponseEntity<TagsDTO> createTags(@Valid @RequestBody TagsDTO tagsDTO) {
        ApiResult<Tags> tag = tagsService.createTag(mapper.toEntity(tagsDTO));
        if (tag.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(tag.getData()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary="deleteTagWithQuestionID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTagWithQuestionID(@PathVariable UUID id) {

        ApiResult<Void> voidApiResult = tagsService.deleteTagWithQuestionsID(id);
        if (voidApiResult.isSuccess()) {

            return ResponseEntity.ok("Tags deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

