package com.example.identityservice.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class PutPermissionRequest {
    Set<String> permissions;
}
