package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Block;
import pdp.gg_store.model.DAO.BlockRepository;
import pdp.gg_store.model.User;
import pdp.gg_store.service.interfaces.BlockService;
import pdp.gg_store.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class BlockServiceImpl implements BlockService {
    private  final BlockRepository blockRepository;
    private  final UserService userService;
    @Override
    public ApiResult<List<Block>> getAllBlocks() {
        List<Block> all = blockRepository.findAll();
        if(all.isEmpty()){
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }

    @Override
    public ApiResult<Block> getBlockById(UUID id) {
        Optional<Block> block=blockRepository.findById(id);
        if(block.isPresent()){
            Block block1= block.get();
            return ApiResult.success(block1);
        }else {
            return ApiResult.error("not found");
        }
    }

    @Override
    public ApiResult<Block>createBlock(Block block) {
        try {
            if (blockRepository.existsById(block.getId())) {
                throw new RuntimeException();
            }
            blockRepository.save(block);
            User blockUser = userService.getByUsername(block.getBlocker().getUsername());
            blockUser.setActive(false);
            User update = userService.update(blockUser);
            return ApiResult.success(block);
        } catch (RuntimeException e) {
            return ApiResult.error("block id is not empty");
        }

    }



    @Override
    public ApiResult<Void> deleteBlock(UUID id) {
        try {
            Optional<Block> optionalBlock = blockRepository.findById(id);
            if (optionalBlock.isPresent()) {
                Block block = optionalBlock.get();
                User blocker = block.getBlocker();
                blocker.setActive(true);
                blockRepository.deleteById(id);
                return ApiResult.success(null);
            }
            throw new RuntimeException();
        } catch (RuntimeException e) {
          return   ApiResult.error("block id not found for delete");
        }


    }
}
