package com.production.manager.service.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.production.manager.dto.product.requests.CreateProductDTO;
import com.production.manager.dto.product.requests.UpdateProductDTO;
import com.production.manager.dto.product.responses.ProductResponseDTO;
import com.production.manager.dto.production.ProductionSuggestionDTO;
import com.production.manager.mapper.ProductMapper;
import com.production.manager.model.Product;
import com.production.manager.model.ProductCompositions;
import com.production.manager.repository.products.IProductRepository;
import com.production.manager.service.IProductService;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<ProductResponseDTO> create(CreateProductDTO dto) {
        var product = productMapper.toEntity(dto);
        var savedProduct = productRepository.save(product);
        return ResponseEntity.created(null).body(productMapper.toResponseDTO(savedProduct));
    }

    @Override
    public ResponseEntity<ProductResponseDTO> update(UUID id, UpdateProductDTO dto) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setName(dto.getName());
            product.get().setPrice(dto.getPrice());
            var updatedProduct = productRepository.save(product.get());
            return ResponseEntity.ok(productMapper.toResponseDTO(updatedProduct));
        } else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<ProductionSuggestionDTO>> getProductionSuggestions() {
        var products = productRepository.findAllByOrderByPriceDesc();
        var suggestions = new ArrayList<ProductionSuggestionDTO>();

        for (Product product : products) {
            if (product.getCompositions().isEmpty())
                continue;

            var maxForThisProduct = Integer.MAX_VALUE;
            for (ProductCompositions comp : product.getCompositions()) {
                var stock = comp.getRawMaterial().getStockQuantity();
                var required = comp.getQuantityRequired();

                var possibleWithThisMaterial = stock.divide(required, 0, RoundingMode.FLOOR).intValue();

                if (possibleWithThisMaterial < maxForThisProduct)
                    maxForThisProduct = possibleWithThisMaterial;
            }

            // calculate the potential revenue
            var revenue = product.getPrice().multiply(new BigDecimal(maxForThisProduct));
            suggestions.add(new ProductionSuggestionDTO(product.getName(), maxForThisProduct, revenue));
        }

        return ResponseEntity.ok(suggestions);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> getById(UUID id) {
        var product = productRepository.findById(id);
        if (product.isPresent())
            return ResponseEntity.ok(productMapper.toResponseDTO(product.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        var products = productRepository.findAll();
        return ResponseEntity.ok(products.stream().map(productMapper::toResponseDTO).toList());
    }
}
