package com.tsimo.spendwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsimo.spendwise.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
public abstract class Transaction extends TimestampedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(index = 1)
    private long id;
    @JsonProperty(index = 2)
    private String name;
    @JsonProperty(index = 3)
    private String details;
    @PositiveOrZero(message = "Amount must be 0 or larger")
    @JsonProperty(index = 4)
    @NotNull(message = "The amount must not be null")
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "The category must not be null")
    private Category category;
}