package com.dino.backend.features.promotion.domain;

import com.dino.backend.shared.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Promotion extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Column(name = "start_date") //, nullable = false)
    Instant startDate;

    @Column(name = "end_date") //, nullable = false)
    Instant endDate;
}
