package com.mishoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level =  AccessLevel.PRIVATE)
@Table
@Entity(name = "size")
public class Size extends  BaseEntity {
    @Column(length = 50, nullable = false)
    String code;
    @Column(length = 250, nullable = false)
    String name ;
}
