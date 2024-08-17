package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Vote;
import pdp.gg_store.model.enums.VoteEnum;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    long countByAnswerAndVoteType(Answer answer, VoteEnum voteEnum);
}