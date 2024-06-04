package com.example.identityservice.service;

import com.example.identityservice.dto.request.PermissionRequest;
import com.example.identityservice.dto.response.PermissionResponse;
import com.example.identityservice.mapper.PermissionMapper;
import com.example.identityservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PermissionService {

    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;

    public Set<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll()
                .stream().map(permissionMapper::toPermissionResponse).collect(Collectors.toSet());
    }

    public PermissionResponse createPermission(PermissionRequest request) {
        return permissionMapper.toPermissionResponse(permissionRepository.save(permissionMapper.toPermission(request)));
    }

    public void deletePermission(String name) {
        permissionRepository.deleteById(name);
    }
}
