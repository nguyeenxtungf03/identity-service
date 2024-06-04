package com.example.identityservice.controller;

import com.example.identityservice.dto.request.PutPermissionRequest;
import com.example.identityservice.dto.request.RoleRequest;
import com.example.identityservice.dto.response.ApiResponse;
import com.example.identityservice.dto.response.RoleResponse;
import com.example.identityservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/role")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleController {

    RoleService roleService;

    @GetMapping
    public ApiResponse<Set<RoleResponse>> getAllRoles() {
        return new ApiResponse<>(roleService.getListRole());
    }

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return new ApiResponse<>(roleService.createRole(roleRequest));
    }

    @DeleteMapping("/{name}")
    public ApiResponse<String> deleteRole(@PathVariable String name) {
        roleService.deleteRole(name);
        return new ApiResponse<>(HttpStatus.OK.name());
    }

    @PostMapping("/{name}/put-permission")
    public ApiResponse<RoleResponse> putPermission(@PathVariable String name, @RequestBody PutPermissionRequest putPermissionRequest) {
        return new ApiResponse<>(roleService.putPermission(name, putPermissionRequest));
    }

}
