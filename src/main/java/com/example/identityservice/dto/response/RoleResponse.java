package com.example.identityservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter @Setter
@Accessors(chain = true)
public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;
}
