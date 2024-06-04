package com.example.identityservice.repository;

import com.example.identityservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    Set<Permission> findByNameIn(Set<String> names);
}
