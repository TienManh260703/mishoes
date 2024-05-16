package com.mishoes.dto.request.create.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @Size(min = 5, max = 50, message = "Username must be at least 5 character")
    String userName;
    @Size(min = 5, max = 50, message = "Password must be at least 5 character")
    String password;
    @NotBlank(message = "First name cannot be left blank")
    String firstName;
    @NotBlank(message = "First name cannot be left blank")
    String lastName;
    @Email
    String email;
    @Size(min = 10, max = 10, message = "Phone number have 10 digits")
    String phone;
    String address;
    boolean gender;
    LocalDate dateOfBirth;
    List<String> roles;
}
