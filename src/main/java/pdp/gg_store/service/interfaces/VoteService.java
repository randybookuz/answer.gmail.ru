package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Vote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoteService {
  ApiResult<Void> vote(Vote vote);
  ApiResult<Long> getLikesCount(UUID answerID);
  ApiResult<Long> getDislikesCount(UUID answerID);

}
