package org.app.gestion_meubles.repository;

import org.app.gestion_meubles.model.entity.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FurnitureRepository extends JpaRepository<Furniture, UUID> {
}
