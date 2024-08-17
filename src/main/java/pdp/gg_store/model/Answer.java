package pdp.gg_store.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import pdp.gg_store.model.audit.AbsDateAudit;
import pdp.gg_store.model.audit.AbsUUIDEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SQLRestriction("deleted=false")
@SQLDelete(sql = "(update answers set deleted=true where id=?)")
@Entity(name = "answer")
public class Answer extends AbsUUIDEntity {

   @Column(name = "content", nullable = false)
   private String content ;
   @ManyToOne
   @JoinColumn(name = "question_id")
   private Question question;
   @Column(name = "rating", nullable = false)
   private Integer rating ;
   @Column(name = "accepted", nullable = false)
   private  boolean accepted;
   @Column(name = "edited", nullable = false)
   private  boolean edited ;







}
