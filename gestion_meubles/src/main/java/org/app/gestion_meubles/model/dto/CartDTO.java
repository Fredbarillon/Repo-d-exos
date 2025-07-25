package org.app.gestion_meubles.model.dto;

import java.util.List;
import java.util.UUID;

public record CartDTO(UUID id,List<CartItemDTO> items,double total) {}
