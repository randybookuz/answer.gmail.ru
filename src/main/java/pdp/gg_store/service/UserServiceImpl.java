package pdp.gg_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pdp.gg_store.model.DAO.UserRepository;
import pdp.gg_store.model.User;
import pdp.gg_store.service.interfaces.UserService;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;


    @Override
    public User create(User user) {
    if(userRepository.existByUsername(user.getUsername())){
        throw  new RuntimeException("user already exist");
    }
    return  userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User update(User user) {
        return    userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
       var username= SecurityContextHolder.getContext().getAuthentication().getName();
       return  getByUsername(username);
    }
    @Override
    public UserDetailsService userDetails(){
        return  this::getByUsername;
    }
}
