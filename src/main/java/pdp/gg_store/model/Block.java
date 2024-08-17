package pdp.gg_store.model;

import jakarta.persistence.*;
import lombok.*;
import pdp.gg_store.model.audit.AbsDateAudit;
import pdp.gg_store.model.audit.AbsUUIDEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "block")
public class Block extends AbsUUIDEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User blocker;
    @Column(name = "reason", nullable = false)
    private  String reason;

}
