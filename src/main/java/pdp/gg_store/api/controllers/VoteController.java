package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.VoteDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.VoteMapper;
import pdp.gg_store.model.Vote;

import pdp.gg_store.service.interfaces.VoteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/vote")
@Tag(name="Vote")
public class VoteController {

    private  final VoteService voteService;
    private  final VoteMapper mapper;




    @Operation(summary="getLikesCount")
    @GetMapping("/likes/{answerId}")
    public ResponseEntity<Long> getLikesCount(@PathVariable UUID answerId) {
        ApiResult<Long> likesCount = voteService.getLikesCount(answerId);
        if(likesCount.isSuccess()){
        return ResponseEntity.ok().body(likesCount.getData());
    }else {
            return ResponseEntity.badRequest().build();
        }}
    @Operation(summary="getDislikesCount")
    @GetMapping("/dislikes/{answerId}")
    public ResponseEntity<Long> getDislikesCount(@PathVariable UUID answerId) {
        ApiResult<Long> dislikesCount = voteService.getDislikesCount(answerId);
        if (dislikesCount.isSuccess()) {
            return ResponseEntity.ok().body(dislikesCount.getData());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary="createVote")
    @PostMapping
    public ResponseEntity<Void> createVote(@Valid @RequestBody VoteDTO voteDTO) {
        ApiResult<Void> vote = voteService.vote(mapper.toEntity(voteDTO));
        if(vote.isSuccess()){

        return ResponseEntity.noContent().build();
    }else {
            return ResponseEntity.badRequest().build();

        }}
}

