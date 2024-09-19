package com.example.demo.controller;


import com.example.demo.etity.Products;
import com.example.demo.repository.CategoriesRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.servicee.ProductRequest;
import com.example.demo.servicee.ProductstResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("SanPham")
public class ProductController {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ProductsRepository productsRepository;


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<ProductstResponse> list = new ArrayList<>();
        productsRepository.findAll().forEach(c -> list.add(c.toResponse()));
        return ResponseEntity.ok(list);
    }

    @GetMapping("page")
    public ResponseEntity<?> page(@RequestParam(defaultValue = "0") Integer page) {
        Pageable p = PageRequest.of(page, 5);
        List<ProductstResponse> list = new ArrayList<>();
        productsRepository.findAll(p).forEach(c -> list.add(c.toResponse()));
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if(productsRepository.findById(id).isPresent()){
            productsRepository.deleteById(id);
            return ResponseEntity.ok("Xóa thành công");
        }else {
            return ResponseEntity.badRequest().body("Không tìm thấy id cần xóa");
        }
    }


    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder mess = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> mess.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.badRequest().body(mess.toString());
        }
        if (productsRepository.existsByProductName(productRequest.getProductName())) {
            return ResponseEntity.badRequest().body("Name đã tồn tại");
        }
        if (!categoriesRepository.existsById(productRequest.getCategoriesID())) {
            return ResponseEntity.badRequest().body("categories k tồn tại");
        }
        Products products = productRequest.toEntity();
        products.setCategories(categoriesRepository.getById(productRequest.getCategoriesID()));
        productsRepository.save(products);
        return ResponseEntity.ok("thêm thành công");
    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody ProductRequest productRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (productsRepository.existsByProductName(productRequest.getProductName())) {
            return ResponseEntity.badRequest().body("product đã tồn tại");
        }
        if (!categoriesRepository.existsById(productRequest.getCategoriesID())) {
            return ResponseEntity.badRequest().body("categories k tồn tại");
        }
        if (productsRepository.findById(id).isPresent()) {
            Products products = productRequest.toEntity();
            products.setId(id);
            products.setCategories(categoriesRepository.getById(productRequest.getCategoriesID()));
            productsRepository.save(products);
            return ResponseEntity.ok("Update thành công ");
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy id cần update");
        }
    }



}
