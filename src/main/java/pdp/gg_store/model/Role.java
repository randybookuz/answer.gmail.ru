package pdp.gg_store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;
import pdp.gg_store.model.audit.AbsUUIDEntity;
import pdp.gg_store.model.enums.PermissionEnum;
import pdp.gg_store.model.enums.RoleEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "roles")
@SQLRestriction("deleted=false")//select * from roles; -> select * from roles where deleted=false
@SQLDelete(sql = "update roles set deleted=true where id=?")//
public class Role extends AbsUUIDEntity {
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private RoleEnum type;

    //    @ElementCollection//role_permissions(role_id,permission)
    @JdbcTypeCode(SqlTypes.ARRAY)//
    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissions;
}
