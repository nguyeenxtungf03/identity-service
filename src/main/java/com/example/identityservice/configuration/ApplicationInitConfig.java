package com.example.identityservice.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.identityservice.model.Role;
import com.example.identityservice.model.User;
import com.example.identityservice.model.UserRoles;
import com.example.identityservice.repository.RoleRepository;
import com.example.identityservice.repository.UserRepository;
import com.example.identityservice.repository.UserRolesRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(
            UserRepository userRepository, RoleRepository roleRepository, UserRolesRepository userRolesRepository) {
        return args -> {
            Role roleAdmin;
            Optional<Role> roleAdminOptional = roleRepository.findById("ADMIN");
            roleAdmin = roleAdminOptional.orElseGet(
                    () -> roleRepository.save(new Role().setName("ADMIN").setDescription("ADMIN")));
            if (roleRepository.findById("USER").isEmpty())
                roleRepository.save(new Role().setName("USER").setDescription("USER"));
            if (userRepository.findByUsername("admin").isEmpty()) {
                User user = new User().setUsername("admin").setPassword(passwordEncoder.encode("admin"));
                userRepository.save(user);
                userRolesRepository.save(new UserRoles().setRole(roleAdmin).setUser(user));
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
