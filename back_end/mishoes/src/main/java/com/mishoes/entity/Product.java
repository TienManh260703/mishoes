package com.mishoes.entity;

import com.mishoes.entity.base.BaseEntity;
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
@Table(name = "product")
@Entity
public class Product extends BaseEntity {
    @Column(length = 50, nullable = false)
    String code;
    @Column(length = 250, nullable = false , unique = true)
    String name ;

}
