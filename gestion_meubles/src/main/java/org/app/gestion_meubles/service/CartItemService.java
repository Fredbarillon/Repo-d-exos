package org.app.gestion_meubles.service;

import org.app.gestion_meubles.interfaces.CartItemInterface;
import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.app.gestion_meubles.model.entity.CartItem;
import org.app.gestion_meubles.repository.CartItemRepository;

import java.util.List;
import java.util.UUID;

public class CartItemService implements CartItemInterface {
    private CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItemDTO> getAllCartItems() {
        List<CartItem> cartItems = CartItemRepository.findAll();
        return UserMapper.usersToUserDTOs(users);
    }

    @Override
    public CartItemDTO addCartItem(CartItemDTO cartItemDTO) throws Exception {
        return null;
    }

    @Override
    public void removeCartItem(UUID id) throws Exception {

    }

    @Override
    public void clearCart() {

    }
}
