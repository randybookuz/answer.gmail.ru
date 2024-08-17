package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.AnswerDTO;
import pdp.gg_store.api.payload.AnswerResponseDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.AnswerMapper;
import pdp.gg_store.model.Answer;
import pdp.gg_store.service.interfaces.AnswerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/answer")
@Tag(name="Answer")
public class AnswerController {

    private  final AnswerService answerService;
    private  final AnswerMapper mapper;

    @Operation(summary="getAllAnswers")
    @GetMapping
    public ResponseEntity<List<AnswerResponseDTO>> getAllAnswers() {
        ApiResult<List<Answer>> allAnswers = answerService.getAllAnswers();
if(allAnswers.isSuccess()) {
    List<AnswerResponseDTO> answerDTOS = mapper.toRDTOs(allAnswers.getData());
    return ResponseEntity.ok(answerDTOS);
}else {
    return  ResponseEntity.notFound().build();
}
    }

    @Operation(summary="getAnswerById")
    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDTO> getAnswerById(@PathVariable UUID id) {

        ApiResult<Answer> answer= answerService.getAnswerById(id);

        if (answer.isSuccess()) {
            return ResponseEntity.ok(mapper.toRDTO(answer.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="createAnswer")
    @PostMapping
    public ResponseEntity<AnswerDTO> createAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        ApiResult<Answer> answer = answerService.createAnswer(mapper.toEntity(answerDTO));
        if(answer.isSuccess()){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(answer.getData()));
    }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="updateAnswer")
    @PutMapping("/{id}")
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable UUID id,
                                                      @Valid @RequestBody AnswerDTO updateAnswerDto)  {
        ApiResult<Answer> answerApiResult = answerService.updateAnswer(id, mapper.toEntity(updateAnswerDto));
        if (answerApiResult.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(answerApiResult.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="deleteAnswer")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable UUID  id) {

        ApiResult<Void> voidApiResult = answerService.deleteAnswer(id);
if(voidApiResult.isSuccess()){
        return ResponseEntity.ok("Answer deleted");
    }else{
return  ResponseEntity.notFound().build();
}
    }
}

