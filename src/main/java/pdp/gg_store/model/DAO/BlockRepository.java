package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.gg_store.model.Block;

import java.util.UUID;

public interface BlockRepository extends JpaRepository<Block, UUID> {
}