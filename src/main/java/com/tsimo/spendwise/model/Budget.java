package com.tsimo.spendwise.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
public class Budget extends TimestampedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(index = 1)
    private long id;
    @JsonProperty(index = 2)
    @PositiveOrZero(message = "Amount must be positive or 0")
    @NotNull(message = "The amount must not be null")
    private BigDecimal amount;
    @JsonProperty(index = 3)
    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private byte month;
    @JsonProperty(index = 4)
    private short year;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "The category must not be null")
    private Category category;

    public long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
