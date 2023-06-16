package com.tsimo.spendwise.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class TimestampedEntity {
    @Column(nullable = false, updatable = false)
    @CreationTimestamp //TODO: Should change to JPA Auditing @CreateDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(index = 101)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(index = 102)
    private LocalDateTime updatedAt;
}
