package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.gg_store.model.Question;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
}