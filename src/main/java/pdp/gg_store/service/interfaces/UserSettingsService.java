package pdp.gg_store.service.interfaces;

import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.UserSettings;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserSettingsService {
    ApiResult<List<UserSettings>> getAllUserSettings();
    ApiResult<UserSettings> getUserSettingsById(UUID id);
    ApiResult<UserSettings> createUserSettings(UserSettings userSettings) ;
    ApiResult<UserSettings> updateUserSettings(UUID id,UserSettings userSettings) ;
    ApiResult<Void> deleteUserSettings(UUID id);
}
