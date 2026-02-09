package com.production.manager.service.product_compositions;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.production.manager.dto.product_compositions.requests.CreateProductCompositionDTO;
import com.production.manager.dto.product_compositions.requests.UpdateProductCompositionDTO;
import com.production.manager.dto.product_compositions.responses.ProductCompositionResponseDTO;
import com.production.manager.mapper.ProductCompositionsMapper;
import com.production.manager.repository.product_compositions.ProductCompositionsRepository;
import com.production.manager.repository.products.IProductRepository;
import com.production.manager.repository.raw_material.IRawMaterialRepository;
import com.production.manager.service.IProductCompositionsService;

@Service
public class ProductCompositionsService implements IProductCompositionsService {

    private final ProductCompositionsRepository productCompositionsRepository;
    private final ProductCompositionsMapper productCompositionsMapper;
    private final IProductRepository productRepository;
    private final IRawMaterialRepository rawMaterialRepository;

    public ProductCompositionsService(ProductCompositionsRepository productCompositionsRepository,
            ProductCompositionsMapper productCompositionsMapper,
            IProductRepository productRepository,
            IRawMaterialRepository rawMaterialRepository) {
        this.productCompositionsRepository = productCompositionsRepository;
        this.productCompositionsMapper = productCompositionsMapper;
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    @Override
    public ResponseEntity<ProductCompositionResponseDTO> create(CreateProductCompositionDTO dto) {
        var product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        var rawMaterial = rawMaterialRepository.findById(dto.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        var productComposition = productCompositionsMapper.toEntity(dto);
        productComposition.setProduct(product);
        productComposition.setRawMaterial(rawMaterial);

        var savedComposition = productCompositionsRepository.save(productComposition);
        return ResponseEntity.created(null).body(productCompositionsMapper.toResponseDTO(savedComposition));
    }

    @Override
    public ResponseEntity<ProductCompositionResponseDTO> getById(UUID id) {
        var productComposition = productCompositionsRepository.findById(id);
        if (productComposition.isPresent())
            return ResponseEntity.ok(productCompositionsMapper.toResponseDTO(productComposition.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<ProductCompositionResponseDTO>> getAll() {
        var productCompositions = productCompositionsRepository.findAll();
        return ResponseEntity.ok(productCompositions.stream().map(productCompositionsMapper::toResponseDTO).toList());
    }

    @Override
    public ResponseEntity<ProductCompositionResponseDTO> update(UUID id, UpdateProductCompositionDTO dto) {
        var productComposition = productCompositionsRepository.findById(id);
        if (productComposition.isPresent()) {
            productComposition.get().setQuantityRequired(dto.getQuantityRequired());
            var updatedProductComposition = productCompositionsRepository.save(productComposition.get());
            return ResponseEntity.ok(productCompositionsMapper.toResponseDTO(updatedProductComposition));
        } else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        var productComposition = productCompositionsRepository.findById(id);
        if (productComposition.isPresent()) {
            productCompositionsRepository.delete(productComposition.get());
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
