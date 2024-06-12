package com.example.identityservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.identityservice.dto.request.UserRequest;
import com.example.identityservice.dto.request.UserUpdateRequest;
import com.example.identityservice.dto.response.UserResponse;
import com.example.identityservice.exception.AppException;
import com.example.identityservice.exception.ErrorCode;
import com.example.identityservice.mapper.RoleMapper;
import com.example.identityservice.mapper.UserMapper;
import com.example.identityservice.model.User;
import com.example.identityservice.model.UserRoles;
import com.example.identityservice.repository.RoleRepository;
import com.example.identityservice.repository.UserRepository;
import com.example.identityservice.repository.UserRolesRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserRolesRepository userRolesRepository;
    RoleMapper roleMapper;

    UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        Set<UserRoles> userRoles = userRolesRepository.findByUserIn(users);

        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(userMapper
                .toUserResponse(user)
                .setRoleResponses(userRoles.stream()
                        .filter(ur -> ur.getUser().equals(user))
                        .map(r -> roleMapper.toRoleResponse(r.getRole()))
                        .collect(Collectors.toSet()))));
        return userResponses;
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Set<UserRoles> userRoles = userRolesRepository.findByUser(user);
        return userMapper
                .toUserResponse(user)
                .setRoleResponses(userRoles.stream()
                        .filter(ur -> ur.getUser().equals(user))
                        .map(r -> roleMapper.toRoleResponse(r.getRole()))
                        .collect(Collectors.toSet()));
    }

    public UserResponse createUser(UserRequest userRequest) {
        var user = userRepository.findByUsername(userRequest.getUsername());
        if (user.isPresent()) throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        User newUser = userRepository.save(userMapper.toUser(userRequest));
        userRolesRepository.save(new UserRoles()
                .setUser(newUser)
                .setRole(roleRepository.findById("USER").orElseThrow()));
        return userMapper.toUserResponse(newUser);
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Transactional
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRolesRepository.deleteByUser(user);
        userRepository.delete(user);
    }
}
