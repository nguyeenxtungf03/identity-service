package com.example.identityservice.service;

import com.example.identityservice.dto.request.PutPermissionRequest;
import com.example.identityservice.dto.request.RoleRequest;
import com.example.identityservice.dto.response.RoleResponse;
import com.example.identityservice.exception.AppException;
import com.example.identityservice.exception.ErrorCode;
import com.example.identityservice.mapper.RoleMapper;
import com.example.identityservice.model.Permission;
import com.example.identityservice.model.Role;
import com.example.identityservice.repository.PermissionRepository;
import com.example.identityservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    RoleMapper roleMapper;

    public Set<RoleResponse> getListRole() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).collect(Collectors.toSet());
    }

    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);
        Set<Permission> permissions = permissionRepository.findByNameIn(roleRequest.getPermissionNames());
        role.setPermissions(permissions);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }

    public RoleResponse putPermission(String name, PutPermissionRequest putPermissionRequest) {
        Role role = roleRepository.findById(name).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        Set<Permission> permissions = permissionRepository.findByNameIn(putPermissionRequest.getPermissions());
        if (permissions.size() != putPermissionRequest.getPermissions().size()) throw new AppException(ErrorCode.PERMISSION_NOT_FOUND);
        role.setPermissions(permissions);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
