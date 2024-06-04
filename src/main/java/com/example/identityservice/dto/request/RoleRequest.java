package com.example.identityservice.dto.request;

import lombok.Getter;

import java.util.Set;

@Getter
public class RoleRequest {
    String name;
    String description;
    Set<String> permissionNames;
}
