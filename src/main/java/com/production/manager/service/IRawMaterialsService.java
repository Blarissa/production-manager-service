package com.production.manager.service;

import org.springframework.http.ResponseEntity;

import com.production.manager.dto.raw_materials.requests.CreateRawMaterialDTO;
import com.production.manager.dto.raw_materials.requests.UpdateRawMaterialDTO;
import com.production.manager.dto.raw_materials.responses.RawMaterialResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IRawMaterialsService {

    ResponseEntity<RawMaterialResponseDTO> create(CreateRawMaterialDTO dto);

    ResponseEntity<RawMaterialResponseDTO> getById(UUID id);

    ResponseEntity<List<RawMaterialResponseDTO>> getAll();

    ResponseEntity<RawMaterialResponseDTO> update(UUID id, UpdateRawMaterialDTO dto);

    ResponseEntity<Void> delete(UUID id);

    void updateStock(UUID id, Double quantityConsumed);
}