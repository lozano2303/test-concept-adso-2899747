package com.ecommerce.order.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.Dto.CartDto;
import com.ecommerce.order.Entity.Cart;
import com.ecommerce.order.IService.ICartService;
import com.ecommerce.user.Entity.User;
import com.ecommerce.user.IService.IUserService;

/**
 * REST Controller for the Cart entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final ICartService cartService;
    private final IUserService userService;

    public CartController(ICartService cartService, IUserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        List<Cart> carts = cartService.findAll();
        List<CartDto> cartDtos = carts.stream().map(cart -> convertToDto(cart)).collect(Collectors.toList());
        return ResponseEntity.ok(cartDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartService.findById(id);
        if (cart.isPresent()) {
            return ResponseEntity.ok(convertToDto(cart.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
        Optional<Cart> cart = cartService.findByUserId(userId);
        if (cart.isPresent()) {
            return ResponseEntity.ok(convertToDto(cart.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        Cart cart = convertToEntity(cartDto);
        Cart savedCart = cartService.save(cart);
        return ResponseEntity.ok(convertToDto(savedCart));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCart(@PathVariable Long id, @RequestBody CartDto cartDto) {
        Optional<Cart> existingCart = cartService.findById(id);
        if (existingCart.isPresent()) {
            Cart cart = existingCart.get();
            // Update user association if userId is provided
            if (cartDto.getUserId() != null) {
                Optional<User> user = userService.findById(cartDto.getUserId());
                if (user.isPresent()) {
                    cart.setUser(user.get());
                } else {
                    // Handle case where user does not exist
                    cart.setUser(null);
                }
            } else {
                // If userId is null, remove association
                cart.setUser(null);
            }
            Cart updatedCart = cartService.save(cart);
            return ResponseEntity.ok(convertToDto(updatedCart));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        if (cartService.findById(id).isPresent()) {
            cartService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private CartDto convertToDto(Cart cart) {
        CartDto dto = new CartDto();
        // User can be null - carts can exist without being associated to a user
        if (cart.getUser() != null) {
            dto.setUserId(cart.getUser().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private Cart convertToEntity(CartDto dto) {
        Cart cart = new Cart();
        // If userId is provided, fetch and set the user
        if (dto.getUserId() != null) {
            Optional<User> user = userService.findById(dto.getUserId());
            if (user.isPresent()) {
                cart.setUser(user.get());
            } else {
                // Handle case where user does not exist - could throw exception or log warning
                // For now, we'll allow the cart to be created without user association
            }
        }
        return cart;
    }
}