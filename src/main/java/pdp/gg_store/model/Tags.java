package pdp.gg_store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import pdp.gg_store.model.audit.AbsUUIDEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "tag")
public class Tags extends AbsUUIDEntity {
    @Column(name = "name", nullable = false, unique = true)
    private  String name;
    @Column(name = "questionId;", nullable = false)
private UUID questionId;
}
