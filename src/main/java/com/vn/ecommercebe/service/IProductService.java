package com.vn.ecommercebe.service;

import com.vn.ecommercebe.model.Product;
import com.vn.ecommercebe.repository.ProductRepo;

import java.util.List;

public interface IProductService {

    void save(Product product);

    List<Product> getAll();

}
