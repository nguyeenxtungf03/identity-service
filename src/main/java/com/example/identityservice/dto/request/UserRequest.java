package com.example.identityservice.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.example.identityservice.validator.DobConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @Size(min = 6, message = "USERNAME_TOO_SHORT")
    @Size(max = 12, message = "USERNAME_TOO_LONG")
    String username;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    @NotBlank(message = "First name cannot blank")
    String firstName;

    @NotBlank(message = "Last name cannot blank")
    String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DobConstraint(min = 18, message = "DOB_INVALID")
    Date dob;
}
