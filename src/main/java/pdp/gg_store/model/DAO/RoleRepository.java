package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.gg_store.model.Role;
import pdp.gg_store.model.enums.RoleEnum;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByType(RoleEnum type);
}