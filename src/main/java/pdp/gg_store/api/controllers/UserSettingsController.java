package pdp.gg_store.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.gg_store.api.exceptions.ApiResult;
import pdp.gg_store.api.payload.UserSettingsDTO;
import pdp.gg_store.api.utils.AppConstants;
import pdp.gg_store.api.mapper.UserSettingsMapper;
import pdp.gg_store.model.UserSettings;
import pdp.gg_store.service.interfaces.UserSettingsService;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 +"/userSettings")
@Tag(name="UserSettings")
public class UserSettingsController {

    private  final UserSettingsService userSettingsService;
    private  final UserSettingsMapper mapper;


    @Operation(summary="Settingss")
    @GetMapping
    public ResponseEntity<List<UserSettingsDTO>> getAllUserSettingss() {
        ApiResult<List<UserSettings>> allUserSettings = userSettingsService.getAllUserSettings();
if(allUserSettings.isSuccess()){
        List<UserSettingsDTO> userSettingsDTOS = mapper.toDTOs(allUserSettings.getData());
        return ResponseEntity.ok(userSettingsDTOS);
    } else {
    return ResponseEntity.notFound().build();
}
    }


    @Operation(summary="getUserSettingsById")
    @GetMapping("/{id}")
    public ResponseEntity<UserSettingsDTO> getUserSettingsById(@PathVariable UUID id) {

        ApiResult<UserSettings> userSettingsById = userSettingsService.getUserSettingsById(id);
        if (userSettingsById.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(userSettingsById.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary="createUserSettings")
    @PostMapping
    public ResponseEntity<UserSettingsDTO> createUserSettings(@Valid @RequestBody UserSettingsDTO userSettingsDTO) {
        ApiResult<UserSettings> userSettings = userSettingsService.createUserSettings(mapper.toEntity(userSettingsDTO));
        if (userSettings.isSuccess()) {

            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(userSettings.getData()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="updateUserSettings")
    @PutMapping("/{id}")
    public ResponseEntity<UserSettingsDTO> updateUserSettings(@PathVariable UUID id,
                                                      @Valid @RequestBody UserSettingsDTO updateUserSettingsDto)  {
        ApiResult<UserSettings> userSettingsApiResult = userSettingsService.updateUserSettings(id, mapper.toEntity(updateUserSettingsDto));
        if (userSettingsApiResult.isSuccess()) {
            return ResponseEntity.ok(mapper.toDTO(userSettingsApiResult.getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary="deleteUserSettings")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserSettings(@PathVariable UUID  id) throws Exception {

        ApiResult<Void> voidApiResult = userSettingsService.deleteUserSettings(id);
if(voidApiResult.isSuccess()){
    return ResponseEntity.ok("UserSettings deleted");
}else{
    return  ResponseEntity.notFound().build();
}


    }
}

