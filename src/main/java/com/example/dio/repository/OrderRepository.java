package com.example.dio.repository;

import com.example.dio.model.CartItem;
import com.example.dio.model.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<TableOrder,Long> {

}
