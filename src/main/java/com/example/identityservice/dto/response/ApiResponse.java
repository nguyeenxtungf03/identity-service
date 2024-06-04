package com.example.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class ApiResponse <T> {
    int code = HttpStatus.OK.value();
    String errorCode;
    String errorMessage;
    String message;
    T result;


    public ApiResponse(T result) {this.result = result;}
}
