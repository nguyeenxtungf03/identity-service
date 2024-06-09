package com.example.identityservice.dto.request;

import com.example.identityservice.validator.DobConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @Size(min = 6, message = "USERNAME_INVALID")
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
