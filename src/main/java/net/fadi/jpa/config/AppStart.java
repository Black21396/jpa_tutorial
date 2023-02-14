package net.fadi.jpa.config;


import net.fadi.jpa.entity.Role;
import net.fadi.jpa.entity.User;
import net.fadi.jpa.repository.RoleRepository;
import net.fadi.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// create some user and role in DB when the application running
@Component
public class AppStart implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0){
            // create init roles
            Role r1 = Role.builder()
                    .name("admin")
                    .build();
            Role r2 = Role.builder()
                    .name("user")
                    .build();

            // save roles in Db
            roleRepository.saveAll(Arrays.asList(r1,r2));

            // Create a set to hold the rules
            Set<Role> roles = new HashSet<>();
            roles.add(r1);
            roles.add(r2);

            // create initial users
            User u1 = User.builder()
                    .email("ali@gmail.com")
                    .password("9022")
                    .roles(roles)
                    .build();

            // save user in DB
            userRepository.save(u1);
        }
    }
}
