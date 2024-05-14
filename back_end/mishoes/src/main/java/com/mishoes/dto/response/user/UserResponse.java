package com.mishoes.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String code;
    @JsonProperty(namespace = "user_name")
    String userName;
//    String password;
    @JsonProperty(namespace = "first_name")
    String firstName;
    @JsonProperty(namespace = "last_name")
    String lastName;
    String email;
    String phone;
    String address;
    boolean gender;
    @JsonProperty(namespace = "date_of_birth")
    LocalDate dateOfBirth;
    @JsonProperty(namespace = "create_at")
    LocalDateTime createAt;
    @JsonProperty(namespace = "update_at")
    LocalDateTime updateAt;
    Set<RoleResponse> roles;
    Integer status;
}
