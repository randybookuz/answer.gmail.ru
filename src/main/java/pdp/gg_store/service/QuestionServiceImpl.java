package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Block;
import pdp.gg_store.model.DAO.QuestionRepository;

import pdp.gg_store.model.Question;
import pdp.gg_store.model.Tags;
import pdp.gg_store.service.interfaces.AnswerService;
import pdp.gg_store.service.interfaces.QuestionService;
import pdp.gg_store.service.interfaces.TagsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final TagsService tagsService;


    @Override
    public ApiResult<List<Question>> getAllQuestions() {
        List<Question> all = questionRepository.findAll();
        if(all.isEmpty()){
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }

    @Override
    public ApiResult<Question> getQuestionById(UUID id) {
       try{ Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setViews(question.getViews() + 1);
            questionRepository.save(question);
            return ApiResult.success(question);
        }else {
            throw new RuntimeException();
        }

    }catch ( RuntimeException e){
       return  ApiResult.error("not found");}
    }

    @Override
    public ApiResult<Question> createQuestion(Question question) {
      try {
          if (questionRepository.existsById(question.getId())) {
              throw new RuntimeException();
          }
          UUID questionID = question.getId();
          question = new Question(question.getTitle(), question.getContent(), question.getCategory(), false, 0, 0, question.getTags(), null);
          question.getTags().stream()
                  .map(tagName -> {
                      Tags tag = new Tags(tagName, questionID);


                      return tagsService.createTag(tag);

                  });


return  ApiResult.success(question);
      }catch (RuntimeException e){
          return  ApiResult.error("id busy for create");
      }
        }

    @Override
    public ApiResult<Question> updateQuestion(UUID id, Question question){
       try{ Optional<Question> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()){
            Question question1 = questionOptional.get();
            question1.setTitle(question.getTitle());
            question1.setContent(question.getContent());
            question1.setCategory(question.getCategory());
            UUID questionID = question.getId();
            tagsService.deleteTagWithQuestionsID(questionID);
            question.getTags().stream()
                    .map(tagName -> {
                        Tags  tag = new Tags(tagName, questionID);



                            return tagsService.createTag(tag);


                    });
            question1.setTags(question.getTags());



        }else{

            throw new RuntimeException();
        }

        return ApiResult.success(question);
    }catch(RuntimeException e){
       return  ApiResult.error("Id not found use create");}
    }

    @Override
    public ApiResult<Void> deleteQuestion(UUID id)  {
      try{  if(questionRepository.existsById(id))
            questionRepository.deleteById(id);
        tagsService.deleteTagWithQuestionsID(id);
        throw  new RuntimeException();
    }catch(RuntimeException e){
      return  ApiResult.error("id not found for delete");
      }
    }

    @Override
    public ApiResult<Boolean> closeQuestion(Question question,Answer bestAnswer) {
       try{ Optional<Question> questionOptional = questionRepository.findById(question.getId());
        Answer updAnswer = answerService.getAnswerById(bestAnswer.getId()).getData();
        if(questionOptional.isPresent()){
            Question updQuestion = questionOptional.get();

            updQuestion.setClosed(true);
            updQuestion.setBestAnswer(bestAnswer);
            updAnswer.setAccepted(true);
            questionRepository.save(updQuestion);
            answerService.updateAnswer(updAnswer.getId(),updAnswer);
            return ApiResult.success(null);

        }else {
            throw new RuntimeException();
        }
    }catch (RuntimeException e){
       return ApiResult.error("close question problem");}
    }

}
