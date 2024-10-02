package com.eazybytes.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@ToString
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false )
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    private String updatedBy;



}
