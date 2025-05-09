package com.dino.backend.features.inventory.domain.entity;

import com.dino.backend.features.product.domain.entity.Product;
import com.dino.backend.shared.model.BaseEntity;
import com.dino.backend.features.shopping.domain.entity.CartItem;
import com.dino.backend.features.shopping.domain.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skus")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE skus SET deleted = true WHERE sku_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sku extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skuId", updatable = false, nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", updatable = false, nullable = false)
    @JsonIgnore
    Product product;

    @OneToOne(mappedBy = "sku", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Inventory inventory;

    @OneToMany(mappedBy = "sku", fetch = FetchType.LAZY)
    @JsonIgnore
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "sku", fetch = FetchType.LAZY)
    @JsonIgnore
    List<OrderItem> orderItems;

    String status; //LIVE, DEACTIVATED

    @Column(nullable = false)
    String skuCode;

    @Column()
    String tierName;

    @Column()
    Integer[] tierIndex;

    Float productCost;

    Float retailPrice;

    Integer carts;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<Specification> specifications;

    // THE NESTED OBJECTS//
    public enum StatusType {LIVE, DEACTIVATED,}

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Specification {
        String name;

        String value;
    }

}
