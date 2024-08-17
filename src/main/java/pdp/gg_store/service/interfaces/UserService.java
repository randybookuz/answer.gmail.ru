package pdp.gg_store.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import pdp.gg_store.model.User;

public interface UserService {

    User create(User user);
    User getByUsername(String username);
    User update(User user);
    User getCurrentUser();
    UserDetailsService userDetails();

}
