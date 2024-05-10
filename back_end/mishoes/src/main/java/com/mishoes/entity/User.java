package com.mishoes.entity;

import com.mishoes.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Table(name = "user")
@Entity
public class User extends BaseEntity {
    String code ;
    @Column(unique = true, length = 100)
    String userName;
    String password;
    @Column(name = "first_name", nullable = false)
    String firstName;
    @Column(name = "last_name", nullable = false)
    String lastName;
    String email;
    String phone;
    String address;
    boolean gender;
    @Column(name = "data_of_birth", nullable = false)
    LocalDate dateOfBirth;
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    com.mishoes.entity.Role roleId;
}
