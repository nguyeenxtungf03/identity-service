package com.example.identityservice.dto.request;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

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
public class UserUpdateRequest {

    @Min(value = 6, message = "Password invalid")
    String password;

    @NotBlank(message = "First name cannot blank")
    String firstName;

    @NotBlank(message = "Last name cannot blank")
    String lastName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date dob;
}
