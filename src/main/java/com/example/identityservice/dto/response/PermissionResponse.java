package com.example.identityservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true)
public class PermissionResponse {
    String name;
    String description;
}
