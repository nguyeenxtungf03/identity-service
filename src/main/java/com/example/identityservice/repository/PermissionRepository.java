package com.example.identityservice.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.identityservice.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    Set<Permission> findByNameIn(Set<String> names);
}
