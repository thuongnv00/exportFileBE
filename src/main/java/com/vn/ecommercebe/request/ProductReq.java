package com.vn.ecommercebe.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReq {

    private String id;

    private String name;

    private String price;

    private String image;
}
