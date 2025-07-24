package org.app.gestion_meubles.interfaces;

import org.app.gestion_meubles.model.dto.CartItemDTO;

import java.util.List;
import java.util.UUID;

public interface CartItemInterface {
    List<CartItemDTO>getAllCartItems();
    CartItemDTO addCartItem(CartItemDTO cartItemDTO) throws Exception;
    void removeCartItem(UUID id)throws Exception;
    void clearCart();
}
