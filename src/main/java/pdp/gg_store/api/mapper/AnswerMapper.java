package pdp.gg_store.api.mapper;

import org.mapstruct.*;
import pdp.gg_store.api.payload.AnswerResponseDTO;
import pdp.gg_store.model.Answer;
import pdp.gg_store.api.payload.AnswerDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {

    @Mapping(source = "questionId", target = "question.id")
    Answer toEntity(AnswerDTO answerDto);

    @Mapping(source = "question.id", target = "questionId")
    AnswerDTO toDTO(Answer answer);
    @Mapping(source = "question.id", target = "questionId")
    AnswerResponseDTO toRDTO(Answer answer);

    @Mapping(source = "question.id", target = "questionId")
    List<AnswerDTO> toDTOs(List<Answer> answers);
    @Mapping(source = "question.id", target = "questionId")
    List<AnswerResponseDTO> toRDTOs(List<Answer> answers);
}