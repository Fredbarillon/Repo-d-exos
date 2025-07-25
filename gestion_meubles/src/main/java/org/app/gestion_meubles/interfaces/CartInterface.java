package org.app.gestion_meubles.interfaces;

import org.app.gestion_meubles.model.dto.AddFurnitureToCartItemDTO;
import org.app.gestion_meubles.model.dto.CartDTO;
import org.app.gestion_meubles.model.dto.CartItemDTO;

import java.util.List;
import java.util.UUID;

public interface CartInterface {
    UUID createCart();
    CartDTO getCart(UUID cartId) throws Exception;
    List<CartItemDTO>getAllCartItemsFromCart(UUID cartId) throws Exception;
    CartItemDTO addCartItemToCart(UUID cartId, AddFurnitureToCartItemDTO addDto) throws Exception;
    void removeCartItemFromCart(UUID cartId,UUID cartItemId)throws Exception;
    void clearCart(UUID cartId) throws Exception;

}
