package pdp.gg_store.api.mapper;

import org.mapstruct.*;

import pdp.gg_store.api.payload.TagsDTO;
import pdp.gg_store.model.Tags;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagsMapper {

    Tags toEntity(TagsDTO tagDTO);

    TagsDTO toDTO(Tags tag);

    List<TagsDTO> toDTOs(List<Tags> tags);

}