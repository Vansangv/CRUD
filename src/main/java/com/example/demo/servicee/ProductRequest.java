package com.example.demo.servicee;


import com.example.demo.etity.Products;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductRequest {


    private Integer id;
    @NotBlank(message = "Name không được để trống")
    private String  productName;

    @NotNull(message = "price không được để trống")
    @Min(value = 1,message = "đơn giá phải là số lớn hơn 0")
    private Double price;

    private String  description;

    @NotNull(message = "categories không được để trống")
    private Integer categoriesID;



    public Products toEntity(){
       return new Products(id,productName,price,description,null);
   }


}
