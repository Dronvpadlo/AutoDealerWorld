package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.repository.PermissionRepository;
import com.example.autodealerworld.repository.RoleRepository;
import com.example.autodealerworld.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RolesDataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public RolesDataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);

            Role buyerRole = new Role();
            buyerRole.setName("BUYER");
            roleRepository.save(buyerRole);

            Role sellerRole = new Role();
            sellerRole.setName("SELLER");
            roleRepository.save(sellerRole);

            Role managerRole = new Role();
            managerRole.setName("MANAGER");
            roleRepository.save(managerRole);
        }

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(roleRepository.findByName("ADMIN").orElseThrow()));
            admin.setProfileType(ProfileType.PREMIUM);
            userRepository.save(admin);
        }
    }

}