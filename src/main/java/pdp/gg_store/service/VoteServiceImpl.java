package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.DAO.AnswerRepository;
import pdp.gg_store.model.DAO.VoteRepository;
import pdp.gg_store.model.Vote;
import pdp.gg_store.model.enums.VoteEnum;
import pdp.gg_store.service.interfaces.VoteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private  final VoteRepository voteRepository;
    private  final AnswerRepository answerRepository;
    @Override
    public ApiResult<Void> vote(Vote vote) {
        Optional<Vote> optionalVote = voteRepository.findById(vote.getId());
        if(optionalVote.isPresent()){
            if(optionalVote.get().getVoteType().equals(vote.getVoteType())){
                voteRepository.delete(vote);
            }else{
                optionalVote.get().setVoteType(vote.getVoteType());
                voteRepository.save( vote);
            }
        }else{
            Vote vote1=new Vote(vote.getVoteType(),vote.getAnswer());
            voteRepository.save(vote1);
        }
        return  ApiResult.success(null);
    }

    @Override
    public ApiResult<Long> getLikesCount(UUID answerID) {

        return ApiResult.success( voteRepository.countByAnswerAndVoteType(answerRepository.findById(answerID).get(), VoteEnum.LIKE));
    }
    @Override
    public ApiResult<Long>getDislikesCount(UUID answerID) {

return  ApiResult.success(voteRepository.countByAnswerAndVoteType(answerRepository.findById(answerID).get(), VoteEnum.DISLIKE));
    }
}
