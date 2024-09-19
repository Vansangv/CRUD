package com.example.demo.etity;

import com.example.demo.servicee.ProductstResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name")
    private String  productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String  description;

    @OneToOne
    @JoinColumn(name = "category_id")
    private categories categories;

    public ProductstResponse toResponse(){
        return new ProductstResponse(id,productName,price,description,categories.getName_category());
    }

}
