package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.CommentDTO;
import pdp.gg_store.api.payload.CommentResponseDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.CommentMapper;
import pdp.gg_store.model.Comment;

import pdp.gg_store.service.interfaces.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/comment")
@Tag(name="Comment")
public class CommentController {

    private  final CommentService commentService;
    private  final CommentMapper mapper;

    @Operation(summary="getAllComments")
    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {
        ApiResult<List<Comment>> allComments = commentService.getAllComments();
if(allComments.isSuccess()){
        List<CommentResponseDTO> commentDTOS = mapper.toRDTOs(allComments.getData());
        return ResponseEntity.ok(commentDTOS);
    }else{
return  ResponseEntity.notFound().build();
}
    }

    @Operation(summary="getCommentById")
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable UUID id) {

        ApiResult<Comment> commentById = commentService.getCommentById(id);
        if (commentById.isSuccess()) {
            return ResponseEntity.ok(mapper.toRDTO(commentById.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="createComment")
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        ApiResult<Comment> comment = commentService.createComment(mapper.toEntity(commentDTO));
        if (comment.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(comment.getData()));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="updateComment")
    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable UUID id,
                                                      @Valid @RequestBody CommentDTO updateCommentDto) {
        ApiResult<Comment> commentApiResult = commentService.updateComment(id, mapper.toEntity(updateCommentDto));
        if (commentApiResult.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(commentApiResult.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="deleteComment")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable UUID  id)  {

        ApiResult<Void> voidApiResult = commentService.deleteComment(id);
if(voidApiResult.isSuccess()){
    return ResponseEntity.ok("Comment deleted");
}else {
    return ResponseEntity.notFound().build();
}


    }
}

