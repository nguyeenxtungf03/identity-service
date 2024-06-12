package com.example.identityservice.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.identityservice.dto.request.UserRequest;
import com.example.identityservice.dto.request.UserUpdateRequest;
import com.example.identityservice.dto.response.ApiResponse;
import com.example.identityservice.dto.response.UserResponse;
import com.example.identityservice.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUser() {
        return new ApiResponse<>(userService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        return new ApiResponse<>(userService.getById(id));
    }

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        return new ApiResponse<>(userService.createUser(userRequest));
    }

    @PutMapping("{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable String id, @RequestBody @Valid UserUpdateRequest request) {
        return new ApiResponse<>(userService.updateUser(id, request));
    }

    @DeleteMapping("{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ApiResponse<>("User has been deleted");
    }
}
