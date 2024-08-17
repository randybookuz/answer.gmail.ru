package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    ApiResult<List<Comment>> getAllComments();
    ApiResult<Comment> getCommentById(UUID id);
    ApiResult<Comment> createComment(Comment comment) ;
    ApiResult<Comment> updateComment(UUID id,Comment comment) ;
    ApiResult<Void>  deleteComment(UUID id) ;
    
}
