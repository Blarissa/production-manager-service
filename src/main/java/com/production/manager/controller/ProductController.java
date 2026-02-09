package com.production.manager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.production.manager.dto.product.requests.CreateProductDTO;
import com.production.manager.dto.product.requests.UpdateProductDTO;
import com.production.manager.dto.product.responses.ProductResponseDTO;
import com.production.manager.dto.production.ProductionSuggestionDTO;
import com.production.manager.service.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Products", description = "API of products management")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ProductResponseDTO> create(@RequestBody CreateProductDTO dto) {
        return productService.create(dto);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Products not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable UUID id) {
        return productService.getById(id);
    }

    @GetMapping("/suggestions")
    @Operation(summary = "Get production suggestions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Production suggestions found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductionSuggestionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Production suggestions not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<ProductionSuggestionDTO>> getProductionSuggestions() {
        return productService.getProductionSuggestions();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateProductDTO dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return productService.delete(id);
    }
}