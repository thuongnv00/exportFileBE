package com.vn.ecommercebe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "ID",unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name="image")
    private String image;

}
