package pdp.gg_store.model.DAO;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pdp.gg_store.model.Tags;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tags, UUID> {


   @Query("select t.questionId from tag t where t.name = :name")
   List<UUID> getQuestionsByTagName(String name);

   @Modifying
   @Query("delete from tag t where t.questionId = :id")
   void deleteTagWithQuestionsID(UUID id);

}