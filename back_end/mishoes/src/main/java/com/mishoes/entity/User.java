package com.mishoes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Table
@Entity(name = "user")
public class User extends BaseEntity{
    String code ;
    @Column(unique = true)
    String userName;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    boolean gender;
    LocalDateTime dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "role_id")
    com.mishoes.entity.Role roleId;
}
