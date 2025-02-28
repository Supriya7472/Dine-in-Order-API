package com.example.dio.module;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="admins")
public class Admin extends User{

}
