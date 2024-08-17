package pdp.gg_store.api.mapper;

import org.mapstruct.*;
import pdp.gg_store.api.payload.VoteDTO;
import pdp.gg_store.model.Vote;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {

    @Mapping(source = "answerId", target = "answer.id")
    Vote toEntity(VoteDTO voteDTO);

    @Mapping(source = "answer.id", target = "answerId")
    VoteDTO toDTO(Vote vote);

    @Mapping(source = "answer.id", target = "answerId")
    List<VoteDTO> toDTOs(List<Vote> votes);
}