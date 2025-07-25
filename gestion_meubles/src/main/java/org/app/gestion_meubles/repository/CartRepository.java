package org.app.gestion_meubles.repository;

import org.app.gestion_meubles.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
