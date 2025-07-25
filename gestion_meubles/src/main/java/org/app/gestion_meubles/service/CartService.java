package org.app.gestion_meubles.service;

import org.app.gestion_meubles.exceptions.CartItemNotFoundException;
import org.app.gestion_meubles.exceptions.CartNotFoundException;
import org.app.gestion_meubles.exceptions.FurnitureNotFoundException;
import org.app.gestion_meubles.exceptions.OutOfStockException;
import org.app.gestion_meubles.interfaces.CartInterface;
import org.app.gestion_meubles.mapper.CartMapper;
import org.app.gestion_meubles.model.dto.AddFurnitureToCartItemDTO;
import org.app.gestion_meubles.model.dto.CartDTO;
import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.app.gestion_meubles.model.entity.Cart;
import org.app.gestion_meubles.model.entity.CartItem;
import org.app.gestion_meubles.model.entity.Furniture;
import org.app.gestion_meubles.repository.CartItemRepository;
import org.app.gestion_meubles.repository.CartRepository;
import org.app.gestion_meubles.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService implements CartInterface {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FurnitureRepository furnitureRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, FurnitureRepository furnitureRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.furnitureRepository = furnitureRepository;
    }

    private double calculateTotal(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getFurniture().getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public UUID createCart() {
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        cart.setTotal(0.0);
        return cartRepository.save(cart).getId();
    }

    public CartDTO getCart(UUID cartId) throws Exception {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Panier non trouvé"));
        return CartMapper.cartToCartDTO(cart);
    }

    @Override
    public List<CartItemDTO> getAllCartItemsFromCart(UUID cartId) throws Exception {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartItemNotFoundException("Panier non trouvé"));
        return CartMapper.cartItemsToCartItemsDTO(cart.getItems());
    }

    @Override
    public CartItemDTO addCartItemToCart(UUID cartId, AddFurnitureToCartItemDTO addDto) throws Exception {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Panier non trouvé"));

        Furniture furniture = furnitureRepository.findById(addDto.getFurnitureId())
                .orElseThrow(() -> new FurnitureNotFoundException("Meuble non trouvé"));

        if (addDto.getQuantity() > furniture.getStock()) {
            throw new OutOfStockException("Stock insuffisant pour ce meuble.");
        }

        CartItem cartItem = new CartItem();
        cartItem.setFurniture(furniture);
        cartItem.setQuantity(addDto.getQuantity());
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);

        cart.setTotal(calculateTotal(cart));

        cartRepository.save(cart);
        return CartMapper.cartItemToCartItemDTO(cartItem);
    }

    @Override
    public void removeCartItemFromCart(UUID cartId, UUID cartItemId) throws Exception {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Panier non trouvé"));

        CartItem toRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("Item non trouvé"));

        cart.getItems().remove(toRemove);
        cartItemRepository.delete(toRemove);

        cart.setTotal(calculateTotal(cart));
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(UUID cartId) throws Exception {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Panier non trouvé"));

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setTotal(0.0);

        cartRepository.save(cart);
    }
}
