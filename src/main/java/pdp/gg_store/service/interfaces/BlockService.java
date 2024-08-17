package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Block;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlockService {
    ApiResult<List<Block>> getAllBlocks();
    ApiResult<Block> getBlockById(UUID id);
    ApiResult<Block> createBlock(Block block) ;

    ApiResult<Void>  deleteBlock(UUID id) ;
}
