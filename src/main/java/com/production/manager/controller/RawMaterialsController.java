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

import com.production.manager.dto.raw_materials.requests.CreateRawMaterialDTO;
import com.production.manager.dto.raw_materials.requests.UpdateRawMaterialDTO;
import com.production.manager.dto.raw_materials.responses.RawMaterialResponseDTO;
import com.production.manager.service.raw_materials.RawMaterialsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Raw Materials", description = "API of raw materials management")
@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialsController {
    private final RawMaterialsService rawMaterialsService;

    public RawMaterialsController(RawMaterialsService rawMaterialsService) {
        this.rawMaterialsService = rawMaterialsService;
    }

    @PostMapping
    @Operation(summary = "Create a new raw material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Raw material created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RawMaterialResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<RawMaterialResponseDTO> create(@RequestBody CreateRawMaterialDTO dto) {
        return rawMaterialsService.create(dto);
    }

    @GetMapping
    @Operation(summary = "Get all raw materials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raw materials found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RawMaterialResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Raw materials not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<RawMaterialResponseDTO>> getAll() {
        return rawMaterialsService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a raw material by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raw material found successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RawMaterialResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Raw material not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<RawMaterialResponseDTO> getById(@PathVariable UUID id) {
        return rawMaterialsService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a raw material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raw material updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RawMaterialResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Raw material not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<RawMaterialResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateRawMaterialDTO dto) {
        return rawMaterialsService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a raw material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raw material deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RawMaterialResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Raw material not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return rawMaterialsService.delete(id);
    }

}
