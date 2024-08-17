package pdp.gg_store.model;

import jakarta.persistence.*;
import lombok.*;

import pdp.gg_store.model.audit.AbsUUIDEntity;
import pdp.gg_store.model.enums.VoteEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "vote")
public class Vote extends AbsUUIDEntity {
   @Enumerated(EnumType.STRING)
   private VoteEnum voteType;

   @ManyToOne
   @JoinColumn(name ="answer_id")
   private Answer answer;

}
