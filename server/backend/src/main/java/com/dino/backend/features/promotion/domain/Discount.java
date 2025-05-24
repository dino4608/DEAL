package com.dino.backend.features.promotion.domain;

import com.dino.backend.features.promotion.domain.model.DiscountPricingType;
import com.dino.backend.features.promotion.domain.model.DiscountStatusType;
import com.dino.backend.features.promotion.domain.model.DiscountType;
import com.dino.backend.features.promotion.domain.model.PriceType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discount_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "discounts")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE discounts SET is_deleted = true WHERE discount_id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Discount extends Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "discount_id", updatable = false, nullable = false)
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", nullable = false)
    DiscountStatusType statusType;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_type", nullable = false)
    DiscountPricingType pricingType;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<DiscountedProductPrice> discountedProductPrices;

}