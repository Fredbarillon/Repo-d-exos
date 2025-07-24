package org.app.gestion_meubles.controller;

import org.app.gestion_meubles.interfaces.CartItemInterface;
import org.app.gestion_meubles.model.dto.AddFurnitureToCartItemDTO;
import org.app.gestion_meubles.model.dto.CartItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    private CartItemInterface cartItemService;

    public CartItemController(CartItemInterface cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        List<CartItemDTO> items = cartItemService.getAllCartItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addCartItem(@Validated @RequestBody AddFurnitureToCartItemDTO addDto) throws Exception {
        CartItemDTO created = cartItemService.addCartItem(addDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable UUID id) throws Exception {
        cartItemService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart() {
        cartItemService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
