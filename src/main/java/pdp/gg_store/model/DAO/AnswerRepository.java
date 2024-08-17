package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.gg_store.model.Answer;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
}