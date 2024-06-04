package com.example.identityservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username cannot blank")
    String username;

    @Min(value = 6, message = "Password invalid")
    String password;

    @NotBlank(message = "First name cannot blank")
    String firstName;

    @NotBlank(message = "Last name cannot blank")
    String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date dob;
}
