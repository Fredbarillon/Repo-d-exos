package org.app.gestion_meubles.mapper;

import org.app.gestion_meubles.model.dto.CartDTO;
import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.app.gestion_meubles.model.entity.Cart;
import org.app.gestion_meubles.model.entity.CartItem;

import java.util.List;
import java.util.UUID;

public class CartMapper {

    public static CartDTO cartToCartDTO(Cart cart) {
        if (cart == null) return null;
        return new CartDTO(
                cart.getId(),
                cartItemsToCartItemsDTO(cart.getItems()),
                cart.getTotal()
        );
    }

    public static List<CartItemDTO> cartItemsToCartItemsDTO(List<CartItem> items) {
        if (items == null) return List.of();
        return items.stream()
                .map(CartMapper::cartItemToCartItemDTO)
                .toList();
    }

    public static CartItemDTO cartItemToCartItemDTO(CartItem item) {
        if (item == null) return null;
        return new CartItemDTO(
                item.getId(),
                item.getFurniture().getName(),
                item.getFurniture().getDescription(),
                item.getFurniture().getPrice(),
                item.getQuantity()
        );
    }


}
