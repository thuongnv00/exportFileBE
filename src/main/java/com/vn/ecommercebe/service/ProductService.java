package com.vn.ecommercebe.service;

import com.vn.ecommercebe.model.Product;
import com.vn.ecommercebe.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    ProductRepo productRepo;


    @Override
    public void save(Product product) {
        try {
            productRepo.save(product);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }
}
