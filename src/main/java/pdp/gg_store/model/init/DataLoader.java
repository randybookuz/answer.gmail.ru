package pdp.gg_store.model.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pdp.gg_store.model.DAO.RoleRepository;
import pdp.gg_store.model.enums.PermissionEnum;
import pdp.gg_store.model.Role;
import pdp.gg_store.model.enums.RoleEnum;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        createUserRole();

        createAdminRole();

    }

    private void createAdminRole() {
        Optional<Role> optionalRole = roleRepository.findByType(RoleEnum.ADMIN);
        if (optionalRole.isPresent())
            return;

        Role role = new Role(
                "Admin",
                "Admin",
                RoleEnum.ADMIN,
                Arrays.stream(PermissionEnum.values()).toList()
        );

        roleRepository.save(role);
    }

    private void createUserRole() {
        Optional<Role> optionalRole = roleRepository.findByType(RoleEnum.USER);
        if (optionalRole.isPresent())
            return;

        Role role = new Role(
                "User",
                "User",
                RoleEnum.USER,
                PermissionEnum.userPermissions()
        );

        roleRepository.save(role);
    }
}
