package com.vn.ecommercebe.controller;

import com.vn.ecommercebe.model.Product;
import com.vn.ecommercebe.request.ProductReq;
import com.vn.ecommercebe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductCtrl {
    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductReq productReq) {
        Product product = new Product();
        product.setId(productReq.getId());
        product.setPrice(productReq.getPrice());
        product.setName(productReq.getName());
        try {
            productService.save(product);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity("them thanh cong!", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(productService.getAll(),HttpStatus.OK);
    }
}
