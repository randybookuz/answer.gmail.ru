package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.model.Answer;
import pdp.gg_store.model.Tags;
import pdp.gg_store.model.UserSettings;
import pdp.gg_store.model.DAO.UserSettingsRepository;
import pdp.gg_store.model.UserSettings;
import pdp.gg_store.service.interfaces.UserSettingsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class UserSettingsServiceImpl implements UserSettingsService {
    private final UserSettingsRepository userSettingsRepository;

    @Override
    public ApiResult<List<UserSettings>> getAllUserSettings() {
        List<UserSettings> all = userSettingsRepository.findAll();
        if (all.isEmpty()) {
            ApiResult.error("not found");
        }
        return ApiResult.success(all);
    }

    @Override
    public ApiResult<UserSettings> getUserSettingsById(UUID id) {
        Optional<UserSettings> userSettings = userSettingsRepository.findById(id);
        if (userSettings.isPresent()) {
            UserSettings userSettings1=userSettings.get();
            return ApiResult.success(userSettings1);
        } else {
            return ApiResult.error("not found");
        }
    }

    @Override
    public ApiResult<UserSettings> createUserSettings(UserSettings userSettings)  {
     try {
         if (userSettingsRepository.existsById(userSettings.getId())) {
             throw new RuntimeException();
         }
         userSettingsRepository.save(userSettings);
         return  ApiResult.success(userSettings);
     }catch ( RuntimeException e){
         return  ApiResult.error("id busy for create");
     }
    }

    @Override
    public ApiResult<UserSettings> updateUserSettings(UUID id, UserSettings userSettings){
       try{ Optional<UserSettings> optionalUserSettings = userSettingsRepository.findById(id);
        if (optionalUserSettings.isPresent()) {
            UserSettings updateUserSettings = optionalUserSettings.get();
            updateUserSettings.setLanguage(userSettings.getLanguage());
            updateUserSettings.setEmailNotification(userSettings.isEmailNotification());
            updateUserSettings.setCommentNotification(userSettings.isCommentNotification());
            updateUserSettings.setPrivateMessages(userSettings.isPrivateMessages());
            userSettingsRepository.save(updateUserSettings);
            return  ApiResult.success(userSettings);

        } else {
            throw new RuntimeException();
        }

    }catch(RuntimeException e){
       return  ApiResult.error("id is empty,use post(create)");
       }
    }

    @Override
    public ApiResult<Void> deleteUserSettings(UUID id) {
        try {
            if (userSettingsRepository.existsById(id)) {
                userSettingsRepository.deleteById(id);
                return ApiResult.success(null);
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            return ApiResult.error("id not found for delete");
        }
    }
}

