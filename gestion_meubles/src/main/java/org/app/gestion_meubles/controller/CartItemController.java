package org.app.gestion_meubles.controller;

import org.app.gestion_meubles.interfaces.CartItemInterface;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartItemController {
    private CartItemInterface cartItemService;

    public CartItemController(CartItemInterface cartItemService) {
        this.cartItemService = cartItemService;
    }

}
