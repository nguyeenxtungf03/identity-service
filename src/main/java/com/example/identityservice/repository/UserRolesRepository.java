package com.example.identityservice.repository;

import com.example.identityservice.model.User;
import com.example.identityservice.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, String> {

    Set<UserRoles> findByUser(User user);

    Set<UserRoles> findByUserIn(List<User> users);

    void deleteByUser(User user);
}
