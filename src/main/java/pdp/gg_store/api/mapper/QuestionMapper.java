package pdp.gg_store.api.mapper;

import jakarta.validation.Valid;
import org.mapstruct.*;
import pdp.gg_store.api.payload.QuestionDTO;
import pdp.gg_store.api.payload.QuestionResponseDTO;
import pdp.gg_store.model.Question;


import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    @Mapping(source = "categoryId", target = "category.id")
    Question toEntity(@Valid QuestionDTO questionDto);

    @Mapping(source = "category.id", target = "categoryId")
    QuestionDTO toDTO(Question question);
    @Mapping(source = "category.id", target = "categoryId")
    QuestionResponseDTO toRDTO(Question question);

    @Mapping(source = "category.id", target = "categoryId")
    List<QuestionDTO> toDTOs(List<Question> questions);
    @Mapping(source = "category.id", target = "categoryId")
    List<QuestionResponseDTO> toRDTOs(List<Question> questions);
}