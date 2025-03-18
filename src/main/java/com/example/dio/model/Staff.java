package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="staffs")
public class Staff extends User{
//    @ManyToMany(mappedBy = "table",fetch = FetchType.EAGER)
//    private List<com.example.dio.model.Table> table;

}
