package pdp.gg_store.api.payload;

import lombok.Value;
import pdp.gg_store.model.UserSettings;
import pdp.gg_store.model.enums.LanguageEnum;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link UserSettings}
 */
@Value
public class UserSettingsDTO implements Serializable {
    UUID id;
    LanguageEnum language;
    boolean emailNotification;
    boolean commentNotification;
    boolean privateMessages;
}