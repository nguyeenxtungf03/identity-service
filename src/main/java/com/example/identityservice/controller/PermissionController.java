package com.example.identityservice.controller;

import com.example.identityservice.dto.request.PermissionRequest;
import com.example.identityservice.dto.response.ApiResponse;
import com.example.identityservice.dto.response.PermissionResponse;
import com.example.identityservice.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/permission")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionController {

    PermissionService permissionService;

    @GetMapping
    public ApiResponse<Set<PermissionResponse>> getAllPermissions() {
        return new ApiResponse<>(permissionService.getAllPermissions());
    }

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {
        return new ApiResponse<>(permissionService.createPermission(permissionRequest));
    }

    @DeleteMapping("/{name}")
    public ApiResponse<String> deletePermission(@PathVariable String name) {
        permissionService.deletePermission(name);
        return new ApiResponse<>(HttpStatus.OK.name());
    }
}
