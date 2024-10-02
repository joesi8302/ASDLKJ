package com.synergisticit.util;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.repository.RoleRepository;
import com.synergisticit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class InitializeData implements CommandLineRunner {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if(userRepository.findAll().isEmpty()){
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setRoleName("USER");
            roleRepository.save(userRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setUserPassword(bCryptPasswordEncoder.encode("admin"));
            admin.setUserEmail("admin@example.com");
            admin.setUserRoles(Collections.singletonList(adminRole));
            userRepository.save(admin);

            User user = new User();
            user.setUsername("user");
            user.setUserPassword(bCryptPasswordEncoder.encode("user"));
            user.setUserEmail("user@example.com");
            user.setUserRoles(Collections.singletonList(userRole));
            userRepository.save(user);
        }

    }
}
