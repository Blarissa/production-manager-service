package com.production.manager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.production.manager.dto.product.requests.CreateProductDTO;
import com.production.manager.dto.product.requests.UpdateProductDTO;
import com.production.manager.dto.product.responses.ProductResponseDTO;
import com.production.manager.dto.production.ProductionSuggestionDTO;

public interface IProductService {
    ResponseEntity<ProductResponseDTO> create(CreateProductDTO dto);

    ResponseEntity<ProductResponseDTO> getById(UUID id);

    ResponseEntity<List<ProductResponseDTO>> getAll();

    ResponseEntity<ProductResponseDTO> update(UUID id, UpdateProductDTO dto);

    ResponseEntity<Void> delete(UUID id);

    ResponseEntity<List<ProductionSuggestionDTO>> getProductionSuggestions();
}
