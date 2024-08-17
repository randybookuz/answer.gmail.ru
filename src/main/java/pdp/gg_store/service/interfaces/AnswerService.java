package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    ApiResult<List<Answer>> getAllAnswers();
    ApiResult<Answer> getAnswerById(UUID id);
    ApiResult<Answer> createAnswer(Answer answer) ;
    ApiResult<Answer> updateAnswer(UUID id, Answer answer);
    ApiResult<Void>  deleteAnswer(UUID id) ;
}
