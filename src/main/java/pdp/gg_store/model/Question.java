package pdp.gg_store.model;

import jakarta.persistence.*;
import lombok.*;
import pdp.gg_store.model.audit.AbsUUIDEntity;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "question")
public class Question extends AbsUUIDEntity {
    @Column(name = "title;", nullable = false, unique = true)
    private String title;
    @Column(name = "content", nullable = false, unique = true)
    private String content;
    @ManyToOne
    @JoinColumn(name ="category_id")
private  Category category;

    @Column(name = "closed", nullable = false)
private  Boolean closed;
    @Column(name = "rating", nullable = false)
private  Integer rating;
    @Column(name = "views", nullable = false)
private  Integer views;
    @ElementCollection
private List<String> tags;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
private  Answer bestAnswer;

}
