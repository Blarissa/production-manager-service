package com.production.manager.repository.raw_material;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.production.manager.model.RawMaterials;

@Repository
public interface IRawMaterialRepository extends JpaRepository<RawMaterials, UUID> {
}
