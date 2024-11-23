package com.example.autodealerworld.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotNull(message = "Username can not be null")
    private String username;

    @NotNull(message = "Password can not be null")
    private String password;

    @NotNull(message = "Password can not be null")
    private String confirmPassword;

    @Email(message = "Email must have @")
    private String email;

    private String phoneNumber;
}
