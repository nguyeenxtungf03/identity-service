package com.example.identityservice.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.identityservice.dto.request.RoleRequest;
import com.example.identityservice.dto.response.PermissionResponse;
import com.example.identityservice.dto.response.RoleResponse;
import com.example.identityservice.model.Role;

@Mapper
public interface RoleMapper {

    @Mapping(source = "role", target = "permissions", qualifiedByName = "roleToRoleResponse")
    RoleResponse toRoleResponse(Role role);

    Role toRole(RoleRequest roleRequest);

    @Named("roleToRoleResponse")
    default Set<PermissionResponse> roleToRoleResponse(Role role) {
        return role.getPermissions().stream()
                .map(x -> new PermissionResponse().setName(x.getName()).setDescription(x.getDescription()))
                .collect(Collectors.toSet());
    }
}
