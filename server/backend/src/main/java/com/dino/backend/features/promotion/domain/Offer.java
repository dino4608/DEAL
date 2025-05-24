package com.dino.backend.features.promotion.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
public abstract class Offer extends Promotion {
}