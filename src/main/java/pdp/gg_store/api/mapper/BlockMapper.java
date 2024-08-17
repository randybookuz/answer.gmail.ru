package pdp.gg_store.api.mapper;

import org.mapstruct.*;
import pdp.gg_store.model.Block;
import pdp.gg_store.api.payload.BlockDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BlockMapper {

    Block toEntity(BlockDTO blockDto);

    BlockDTO toDTO(Block block);

    List<BlockDTO> toDTOs(List<Block> blocks);
}