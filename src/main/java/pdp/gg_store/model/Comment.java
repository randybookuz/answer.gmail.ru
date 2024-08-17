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
@Entity(name = "comment")
public class Comment extends AbsUUIDEntity {
    @ManyToOne
    @JoinColumn(name ="question_id")
    private  Question question;
    @ManyToOne
    @JoinColumn(name ="answer_id")
    private Answer answer;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "edited", nullable = false)
    private  boolean edited;
    }
