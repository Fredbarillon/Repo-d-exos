package org.app.gestion_meubles.service;

import org.app.gestion_meubles.interfaces.CartItemInterface;
import org.app.gestion_meubles.mapper.CartItemMapper;
import org.app.gestion_meubles.model.dto.AddFurnitureToCartItemDTO;
import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.app.gestion_meubles.model.entity.CartItem;
import org.app.gestion_meubles.model.entity.Furniture;
import org.app.gestion_meubles.repository.CartItemRepository;
import org.app.gestion_meubles.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartItemService implements CartItemInterface {
    private final CartItemRepository cartItemRepository;
    private final FurnitureRepository furnitureRepository;

    public CartItemService(CartItemRepository cartItemRepository, FurnitureRepository furnitureRepository) {
        this.cartItemRepository = cartItemRepository;
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public List<CartItemDTO> getAllCartItems() {
        List<CartItem> items = cartItemRepository.findAll();
        return CartItemMapper.cartItemsToCartItemsDTO(items);
    }

    @Override
    public CartItemDTO addCartItem(AddFurnitureToCartItemDTO addDto) throws Exception {
        Furniture furniture = furnitureRepository.findById(addDto.getFurnitureId())
                .orElseThrow(() -> new Exception("Furniture not found"));

        CartItem cartItem = new CartItem();
        cartItem.setFurniture(furniture);
        cartItem.setQuantity(addDto.getQuantity());

        CartItem saved = cartItemRepository.save(cartItem);
        return CartItemMapper.cartItemToCartItemDTO(saved);
    }

    @Override
    public void removeCartItem(UUID id) throws Exception {
        if (!cartItemRepository.existsById(id)) {
            throw new Exception("CartItem not found");
        }
        cartItemRepository.deleteById(id);
    }

    @Override
    public void clearCart() {
        cartItemRepository.deleteAll();
    }


}
