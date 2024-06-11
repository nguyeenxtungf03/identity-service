package com.example.identityservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogoutRequest {
    private String token;
}
