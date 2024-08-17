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
@Entity(name = "category")
public class Category extends AbsUUIDEntity {
    @Column(name = "name", nullable = false, unique = true)
    private  String name;
    @Column(name = "description", nullable = false, unique = true)
    private  String description;
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private  Category parentCategory;
}
