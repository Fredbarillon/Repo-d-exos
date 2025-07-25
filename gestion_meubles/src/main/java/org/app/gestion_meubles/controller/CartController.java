package org.app.gestion_meubles.controller;

import org.app.gestion_meubles.interfaces.CartInterface;
import org.app.gestion_meubles.model.dto.AddFurnitureToCartItemDTO;
import org.app.gestion_meubles.model.dto.CartDTO;
import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.app.gestion_meubles.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartInterface cartService;

    public CartController(CartInterface cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<UUID> createCart() {
        UUID cartId = cartService.createCart();
        return new ResponseEntity<>(cartId, HttpStatus.CREATED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable UUID cartId) throws Exception {
        CartDTO cartDTO = cartService.getCart(cartId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartItemDTO> addCartItem(
            @PathVariable UUID cartId,
            @RequestBody AddFurnitureToCartItemDTO addDto) throws Exception {
        CartItemDTO itemDTO = cartService.addCartItemToCart(cartId, addDto);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(
            @PathVariable UUID cartId,
            @PathVariable UUID cartItemId) throws Exception {
        cartService.removeCartItemFromCart(cartId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) throws Exception {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItemDTO>> getAllItems(@PathVariable UUID cartId) throws Exception {
        List<CartItemDTO> items = cartService.getAllCartItemsFromCart(cartId);
        return ResponseEntity.ok(items);
    }
}
