package com.example.demo.servicee;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductstResponse {

    private Integer id;

    private String  productName;

    private Double price;

    private String  description;

    private String name_category;

}
