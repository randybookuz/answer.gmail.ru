package pdp.gg_store.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.gg_store.model.User;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT  count(u)>0   from  user u where u.username=:username")
    boolean existByUsername(String username);
    User findByUsername(String username);

}