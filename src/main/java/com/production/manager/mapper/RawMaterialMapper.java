package com.production.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.production.manager.dto.raw_materials.requests.CreateRawMaterialDTO;
import com.production.manager.dto.raw_materials.responses.RawMaterialResponseDTO;
import com.production.manager.model.RawMaterials;

@Mapper(componentModel = "spring")
public interface RawMaterialMapper {
    RawMaterialResponseDTO toResponseDTO(RawMaterials rawMaterial);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RawMaterials toEntity(CreateRawMaterialDTO dto);
}
