package org.app.gestion_meubles.mapper;

import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.app.gestion_meubles.model.entity.CartItem;

import java.util.List;

public class CartItemMapper {
    public static CartItemDTO cartItemToCartItemDTO(CartItem cartItem) {
        if (cartItem == null || cartItem.getFurniture() == null) return null;

        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getFurniture().getName(),
                cartItem.getFurniture().getDescription(),
                cartItem.getFurniture().getPrice(),
                cartItem.getQuantity()
        );
    }

    public static List<CartItemDTO> cartItemsToCartItemsDTO(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(CartItemMapper::cartItemToCartItemDTO)
                .toList();
    }


}
