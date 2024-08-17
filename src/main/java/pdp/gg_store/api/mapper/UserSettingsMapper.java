package pdp.gg_store.api.mapper;

import org.mapstruct.*;
import pdp.gg_store.model.UserSettings;
import pdp.gg_store.api.payload.UserSettingsDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserSettingsMapper {

    UserSettings toEntity(UserSettingsDTO userSettingsDto);

    UserSettingsDTO toDTO(UserSettings userSettings);

    List<UserSettingsDTO> toDTOs(List<UserSettings> userSettings);
}