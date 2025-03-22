package com.example.dio.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name="staffs")
@EntityListeners(AuditingEntityListener.class)
public class Staff extends User{

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

}
