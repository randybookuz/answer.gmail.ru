package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.DAO.AnswerRepository;
import pdp.gg_store.service.interfaces.AnswerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private  final AnswerRepository answerRepository;
    @Override
    public ApiResult<List<Answer>> getAllAnswers() {
        List<Answer> all = answerRepository.findAll();
        if(all.isEmpty()){
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }

    @Override
    public ApiResult<Answer> getAnswerById(UUID id) {
        Optional<Answer> answer=answerRepository.findById(id);
        if(answer.isPresent()){
            Answer answer1 = answer.get();
            return ApiResult.success(answer1);
        }else {
            return ApiResult.error("not found");
        }
    }

    @Override
    public ApiResult<Answer> createAnswer(Answer answer) {
     try {
         if (answerRepository.existsById(answer.getId())) {
             throw new RuntimeException();
         }
         answer = new Answer(answer.getContent(), answer.getQuestion(), 0, false, false);
         answerRepository.save(answer);
         return ApiResult.success(answer);
     }catch (RuntimeException e){}
        return ApiResult.error("id busy for create");
    }

    @Override
    public ApiResult<Answer> updateAnswer(UUID id, Answer answer)  {
     try{   Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            Answer updateAnswer = optionalAnswer.get();
            updateAnswer.setContent(answer.getContent());
            if ((!answer.getContent().equals(updateAnswer.getContent())) || (!answer.getQuestion().equals(updateAnswer.getQuestion()))) {
                updateAnswer.setEdited(true);
            }
            updateAnswer.setAccepted(answer.isAccepted());
            updateAnswer.setRating(answer.getRating());
            answerRepository.save(updateAnswer);
            return ApiResult.success(updateAnswer);

        } else {
            throw new RuntimeException();
        }}catch (RuntimeException e){}
        return ApiResult.error("id is empty,use post(create)");

    }

    @Override
    public ApiResult<Void> deleteAnswer(UUID id) {
        try {
            if (answerRepository.existsById(id)){
                answerRepository.deleteById(id);
                return ApiResult.success(null);
        }else{
            throw new RuntimeException();
        }
        } catch(RuntimeException e){
            return ApiResult.error("id not found for delete");
        }
    }



}

