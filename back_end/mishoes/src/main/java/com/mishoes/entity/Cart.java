package com.mishoes.entity;

import com.mishoes.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    User userId;
}
