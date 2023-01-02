package com.microservice.productservice.controller;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    /**
     * It's advisable to use Constructor Dependency Injection Method or simply the @RequiredArgsConstructor from Lombok
     * because it makes sure the dependency is being injected when spring boot application is starting and is not null.
     * @Autowired annotation will set the dependency to null if it can't find it in the Spring Context.
     * This can result in NullPointerException.
     * For example, imagine you provide a repository using @Autowired
     * and spring couldn't find your repository due to the missing @Repository annotation on your repository class.
     * The repository variable will be set to null.
     * Then your code will call the save() method of the repository class then you receive a NullPointerException.
     * */

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

}
