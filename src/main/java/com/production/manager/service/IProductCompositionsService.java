package com.production.manager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.production.manager.dto.product_compositions.requests.CreateProductCompositionDTO;
import com.production.manager.dto.product_compositions.requests.UpdateProductCompositionDTO;
import com.production.manager.dto.product_compositions.responses.ProductCompositionResponseDTO;

public interface IProductCompositionsService {
    ResponseEntity<ProductCompositionResponseDTO> create(CreateProductCompositionDTO dto);

    ResponseEntity<ProductCompositionResponseDTO> getById(UUID id);

    ResponseEntity<List<ProductCompositionResponseDTO>> getAll();

    ResponseEntity<ProductCompositionResponseDTO> update(UUID id, UpdateProductCompositionDTO dto);

    ResponseEntity<Void> delete(UUID id);
}
