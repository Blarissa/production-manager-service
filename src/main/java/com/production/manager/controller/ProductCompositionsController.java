package com.production.manager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.production.manager.dto.product_compositions.requests.CreateProductCompositionDTO;
import com.production.manager.dto.product_compositions.requests.UpdateProductCompositionDTO;
import com.production.manager.dto.product_compositions.responses.ProductCompositionResponseDTO;
import com.production.manager.service.product_compositions.ProductCompositionsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product Compositions", description = "API of product compositions management")
@RestController
@RequestMapping("/api/product-compositions")
public class ProductCompositionsController {

    private final ProductCompositionsService productCompositionsService;

    public ProductCompositionsController(ProductCompositionsService productCompositionsService) {
        this.productCompositionsService = productCompositionsService;
    }

    @PostMapping
    public ResponseEntity<ProductCompositionResponseDTO> create(@RequestBody CreateProductCompositionDTO dto) {
        return productCompositionsService.create(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCompositionResponseDTO> getById(@PathVariable UUID id) {
        return productCompositionsService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<ProductCompositionResponseDTO>> getAll() {
        return productCompositionsService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCompositionResponseDTO> update(@PathVariable UUID id,
            @RequestBody UpdateProductCompositionDTO dto) {
        return productCompositionsService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return productCompositionsService.delete(id);
    }

}
