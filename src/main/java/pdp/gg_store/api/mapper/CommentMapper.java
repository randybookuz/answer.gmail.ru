package pdp.gg_store.api.mapper;

import org.mapstruct.*;
import pdp.gg_store.api.payload.CommentResponseDTO;
import pdp.gg_store.model.Comment;
import pdp.gg_store.api.payload.CommentDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    @Mapping(source = "answerId", target = "answer.id")
    @Mapping(source = "questionId", target = "question.id")
    Comment toEntity(CommentDTO commentDto);

    @Mapping(source = "answer.id", target = "answerId")
    @Mapping(source = "question.id", target = "questionId")
    CommentDTO toDTO(Comment comment);
    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "answer.id", target = "answerId")
    CommentResponseDTO toRDTO(Comment comment);

    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "answer.id", target = "answerId")
    List<CommentResponseDTO> toRDTOs(List<Comment> comments);
}