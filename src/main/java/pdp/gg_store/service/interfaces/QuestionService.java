package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {
    ApiResult<List<Question>> getAllQuestions();
    ApiResult<Question> getQuestionById(UUID id);
    ApiResult<Question> createQuestion(Question question) ;
    ApiResult<Question> updateQuestion(UUID id, Question question) ;
    ApiResult<Void>  deleteQuestion(UUID id) ;
    ApiResult<Boolean> closeQuestion(Question question,Answer bestAnswer) ;
}
