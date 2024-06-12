package com.example.identityservice.dto.response;

import java.util.Date;
import java.util.Set;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;

    String username;

    String password;

    String firstName;

    String lastName;

    Date dob;

    Set<RoleResponse> roleResponses;
}
