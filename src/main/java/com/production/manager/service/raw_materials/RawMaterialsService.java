package com.production.manager.service.raw_materials;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.production.manager.dto.raw_materials.requests.CreateRawMaterialDTO;
import com.production.manager.dto.raw_materials.requests.UpdateRawMaterialDTO;
import com.production.manager.dto.raw_materials.responses.RawMaterialResponseDTO;
import com.production.manager.mapper.RawMaterialMapper;
import com.production.manager.repository.raw_material.IRawMaterialRepository;
import com.production.manager.service.IRawMaterialsService;

import jakarta.transaction.Transactional;

@Service
public class RawMaterialsService implements IRawMaterialsService {

    private final IRawMaterialRepository rawMaterialRepository;
    private final RawMaterialMapper rawMaterialMapper;

    public RawMaterialsService(IRawMaterialRepository rawMaterialRepository, RawMaterialMapper rawMaterialMapper) {
        this.rawMaterialRepository = rawMaterialRepository;
        this.rawMaterialMapper = rawMaterialMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<RawMaterialResponseDTO> create(CreateRawMaterialDTO dto) {
        var rawMaterial = rawMaterialMapper.toEntity(dto);
        var savedRawMaterial = rawMaterialRepository.save(rawMaterial);
        return ResponseEntity.created(null).body(rawMaterialMapper.toResponseDTO(savedRawMaterial));
    }

    @Override
    public ResponseEntity<RawMaterialResponseDTO> getById(UUID id) {
        var rawMaterial = rawMaterialRepository.findById(id);
        if (rawMaterial.isPresent())
            return ResponseEntity.ok(rawMaterialMapper.toResponseDTO(rawMaterial.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<RawMaterialResponseDTO>> getAll() {
        var rawMaterials = rawMaterialRepository.findAll();
        return ResponseEntity.ok(rawMaterials.stream().map(rawMaterialMapper::toResponseDTO).toList());
    }

    @Override
    public ResponseEntity<RawMaterialResponseDTO> update(UUID id, UpdateRawMaterialDTO dto) {
        var rawMaterial = rawMaterialRepository.findById(id);
        if (rawMaterial.isPresent()) {
            rawMaterial.get().setName(dto.getName());
            rawMaterial.get().setStockQuantity(dto.getStockQuantity());
            var updatedRawMaterial = rawMaterialRepository.save(rawMaterial.get());
            return ResponseEntity.ok(rawMaterialMapper.toResponseDTO(updatedRawMaterial));
        } else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        var rawMaterial = rawMaterialRepository.findById(id);
        if (rawMaterial.isPresent()) {
            rawMaterialRepository.delete(rawMaterial.get());
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.notFound().build();
    }

    @Override
    public void updateStock(UUID id, Double quantityConsumed) {
        var rawMaterial = rawMaterialRepository.findById(id);
        if (rawMaterial.isPresent()) {
            var newStock = rawMaterial.get().getStockQuantity().subtract(BigDecimal.valueOf(quantityConsumed));
            rawMaterial.get().setStockQuantity(newStock);
            rawMaterialRepository.save(rawMaterial.get());
        } else
            throw new RuntimeException("Raw material not found");
    }

}
