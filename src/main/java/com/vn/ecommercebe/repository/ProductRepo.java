package com.vn.ecommercebe.repository;

import com.vn.ecommercebe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {

}
