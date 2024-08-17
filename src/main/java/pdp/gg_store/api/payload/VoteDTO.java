package pdp.gg_store.api.payload;

import lombok.Value;
import pdp.gg_store.model.Vote;
import pdp.gg_store.model.enums.VoteEnum;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Vote}
 */
@Value
public class VoteDTO implements Serializable {
    UUID id;
    VoteEnum voteType;
    UUID answerId;

}