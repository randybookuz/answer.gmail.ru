package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.gg_store.model.UserSettings;

import java.util.UUID;

public interface UserSettingsRepository extends JpaRepository<UserSettings, UUID> {
}