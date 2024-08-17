package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.mapper.AnswerMapper;
import pdp.gg_store.api.payload.AnswerDTO;
import pdp.gg_store.api.payload.QuestionDTO;
import pdp.gg_store.api.payload.QuestionResponseDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.QuestionMapper;
import pdp.gg_store.model.Question;

import pdp.gg_store.service.interfaces.QuestionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/question")
@Tag(name="Question")
public class QuestionController {

    private  final QuestionService questionService;
    private  final QuestionMapper mapper;
    private  final AnswerMapper mapper2;

    @Operation(summary="deleteAnswer")
    @GetMapping
    public ResponseEntity<List<QuestionResponseDTO>> getAllQuestions() {
        ApiResult<List<Question>> allQuestions = questionService.getAllQuestions();
if(allQuestions.isSuccess()){
        List<QuestionResponseDTO> questionDTOS = mapper.toRDTOs(allQuestions.getData());
        return ResponseEntity.ok(questionDTOS);
    }else {
    return ResponseEntity.notFound().build();
}}

    @Operation(summary="getQuestionById")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDTO> getQuestionById(@PathVariable UUID id) {

        ApiResult<Question> questionById = questionService.getQuestionById(id);
        if (questionById.isSuccess()) {
            return ResponseEntity.ok(mapper.toRDTO(questionById.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="createQuestion")
    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO)  {
        ApiResult<Question> question = questionService.createQuestion(mapper.toEntity(questionDTO));
        if(question.isSuccess()){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(question.getData()));
    }else {
            return ResponseEntity.badRequest().build();
        }}

    @Operation(summary=" closeQuestion")
    @PostMapping("/close")
    public ResponseEntity<Boolean> closeQuestion(@Valid @RequestBody QuestionDTO questionDTO, AnswerDTO bestAnswer)  {
        ApiResult<Boolean> booleanApiResult = questionService.closeQuestion(mapper.toEntity(questionDTO), mapper2.toEntity(bestAnswer));

       if(booleanApiResult.isSuccess()){
           return ResponseEntity.ok().body(booleanApiResult.getData());
       }else{
          return ResponseEntity.badRequest().build();
       }
    }

    @Operation(summary="updateQuestion")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable UUID id,
                                                      @Valid @RequestBody QuestionDTO updateQuestionDto) {
        ApiResult<Question> questionApiResult = questionService.updateQuestion(id, mapper.toEntity(updateQuestionDto));
        if (questionApiResult.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(questionApiResult.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="deleteQuestion(")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable UUID  id)  {

        ApiResult<Void> voidApiResult = questionService.deleteQuestion(id);

if(voidApiResult.isSuccess()){
        return ResponseEntity.ok("question deleted");
    }else{ return  ResponseEntity.notFound().build();}
}
}
