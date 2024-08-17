package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Block;
import pdp.gg_store.model.Comment;
import pdp.gg_store.model.DAO.CommentRepository;
import pdp.gg_store.service.interfaces.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private  final CommentRepository commentRepository;
    @Override
    public ApiResult<List<Comment>> getAllComments() {
        List<Comment> all = commentRepository.findAll();
        if(all.isEmpty()){
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }

    @Override
    public ApiResult<Comment> getCommentById(UUID id) {
        Optional<Comment> comment=commentRepository.findById(id);
        if(comment.isPresent()){
            Comment comment1=comment.get();
            return ApiResult.success(comment1);
        }else {
            return ApiResult.error("not found");
        }
    }

    @Override
    public ApiResult<Comment> createComment(Comment comment) {
     try{   if(commentRepository.existsById(comment.getId())) {
            throw new RuntimeException();
        }  commentRepository.save(comment);
         return ApiResult.success(comment);
    }catch (RuntimeException e){
         return ApiResult.error("id busy for create");
     }}

    @Override
    public ApiResult<Comment> updateComment(UUID id, Comment comment)  {
       try{ Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment updateComment = optionalComment.get();
            updateComment.setAnswer(comment.getAnswer());
            updateComment.setQuestion(comment.getQuestion());
            updateComment.setContent(comment.getContent());
            if ((!comment.getContent().equals(comment.getContent())) || (!comment.getQuestion().equals(updateComment.getQuestion()))||(!comment.getAnswer().equals(updateComment.getAnswer()))) {
                updateComment.setEdited(true);
            }

             commentRepository.save(updateComment);
            return ApiResult.success(updateComment);

        } else {
            throw new RuntimeException();
        }}catch (RuntimeException e){
            return  ApiResult.error("id is empty,use post(create)");
       }


    }

    @Override
    public ApiResult<Void> deleteComment(UUID id) {
        try {
            if (commentRepository.existsById(id)) {
                commentRepository.deleteById(id);
                return ApiResult.success(null);
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            return ApiResult.error("id not found for delete");
        }

    }}

