package com.mishoes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Table(name = "role")
@Entity
public class Role  {
    @Id
    String name;
    String description;
    @ManyToMany(fetch = FetchType.LAZY)
    Set<Permission>  permissions;
}
