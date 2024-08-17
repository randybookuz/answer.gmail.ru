package pdp.gg_store.model;

import jakarta.persistence.*;
import lombok.*;
import pdp.gg_store.model.audit.AbsDateAudit;
import pdp.gg_store.model.audit.AbsUUIDEntity;
import pdp.gg_store.model.enums.LanguageEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "userSettings")
public class UserSettings extends AbsUUIDEntity {
    @OneToOne(mappedBy = "settings")
    private User user;
    @Enumerated(EnumType.STRING)
   private LanguageEnum language;
    @Column(name = "emailNotification", nullable = false)
   private  boolean emailNotification;
    @Column(name = "commentNotification", nullable = false)
   private boolean commentNotification;
    @Column(name = "privateMessages", nullable = false)
   private boolean privateMessages;

}
