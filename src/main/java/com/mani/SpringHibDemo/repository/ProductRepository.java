package com.mani.SpringHibDemo.repository;

import com.mani.SpringHibDemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
