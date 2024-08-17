package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.BlockDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.BlockMapper;
import pdp.gg_store.model.Block;
import pdp.gg_store.service.interfaces.BlockService;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/block")
@Tag(name="Block")
public class BlockController {

    private  final BlockService blockService;
    private  final BlockMapper mapper;

    @Operation(summary="getAllBlocks")
    @GetMapping
    public ResponseEntity<List<BlockDTO>> getAllBlocks() {
        ApiResult<List<Block>> allBlocks = blockService.getAllBlocks();
if(allBlocks.isSuccess()){
        List<BlockDTO> blockDTOS = mapper.toDTOs(allBlocks.getData());
        return ResponseEntity.ok(blockDTOS);
    }else {
return ResponseEntity.notFound().build();}
    }

    @Operation(summary="getBlockById(")
    @GetMapping("/{id}")
    public ResponseEntity<BlockDTO> getBlockById(@PathVariable UUID id) {

        ApiResult<Block> blockById = blockService.getBlockById(id);
        if (blockById.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(blockById.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary="createBlock")
    @PostMapping
    public ResponseEntity<BlockDTO> createBlock(@Valid @RequestBody BlockDTO blockDTO) {
        ApiResult<Block> blocks = blockService.createBlock(mapper.toEntity(blockDTO));
        if(blocks.isSuccess()){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(blocks.getData()));
    }else{
         return    ResponseEntity.badRequest().build();
        }
    }


    @Operation(summary="deleteBlock")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlock(@PathVariable UUID  id) {

   blockService.deleteBlock(id);


        return ResponseEntity.ok("Block deleted");
    }
}

