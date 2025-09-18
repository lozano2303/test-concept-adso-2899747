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

import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.catalog.IService.IBookService;
import com.ecommerce.order.Dto.CartItemDto;
import com.ecommerce.order.Entity.Cart;
import com.ecommerce.order.Entity.CartItem;
import com.ecommerce.order.IService.ICartItemService;
import com.ecommerce.order.IService.ICartService;

/**
 * REST Controller for the CartItem entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final IBookService bookService;
    private final ICartService cartService;

    public CartItemController(ICartItemService cartItemService, IBookService bookService, ICartService cartService) {
        this.cartItemService = cartItemService;
        this.bookService = bookService;
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.findAll();
        List<CartItemDto> cartItemDtos = cartItems.stream().map(cartItem -> convertToDto(cartItem)).collect(Collectors.toList());
        return ResponseEntity.ok(cartItemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItemById(@PathVariable Long id) {
        Optional<CartItem> cartItem = cartItemService.findById(id);
        if (cartItem.isPresent()) {
            return ResponseEntity.ok(convertToDto(cartItem.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemDto>> getCartItemsByCartId(@PathVariable Long cartId) {
        List<CartItem> cartItems = cartItemService.findByCartId(cartId);
        List<CartItemDto> cartItemDtos = cartItems.stream().map(cartItem -> convertToDto(cartItem)).collect(Collectors.toList());
        return ResponseEntity.ok(cartItemDtos);
    }

    @PostMapping
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = convertToEntity(cartItemDto);
        CartItem savedCartItem = cartItemService.save(cartItem);
        return ResponseEntity.ok(convertToDto(savedCartItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItem(@PathVariable Long id, @RequestBody CartItemDto cartItemDto) {
        Optional<CartItem> existingCartItem = cartItemService.findById(id);
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setCantidad(cartItemDto.getCantidad());
            // Update book association if bookId is provided
            if (cartItemDto.getBookId() != null) {
                Optional<Book> book = bookService.findById(cartItemDto.getBookId());
                if (book.isPresent()) {
                    cartItem.setBook(book.get());
                } else {
                    // Handle case where book does not exist
                    cartItem.setBook(null);
                }
            } else {
                // If bookId is null, remove association
                cartItem.setBook(null);
            }
            // Update cart association if cartId is provided
            if (cartItemDto.getCartId() != null) {
                Optional<Cart> cart = cartService.findById(cartItemDto.getCartId());
                if (cart.isPresent()) {
                    cartItem.setCart(cart.get());
                } else {
                    // Handle case where cart does not exist
                    cartItem.setCart(null);
                }
            } else {
                // If cartId is null, remove association
                cartItem.setCart(null);
            }
            CartItem updatedCartItem = cartItemService.save(cartItem);
            return ResponseEntity.ok(convertToDto(updatedCartItem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        if (cartItemService.findById(id).isPresent()) {
            cartItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private CartItemDto convertToDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setCantidad(cartItem.getCantidad());
        // Book can be null - cart items can exist without being associated to a book
        if (cartItem.getBook() != null) {
            dto.setBookId(cartItem.getBook().getId());
        }
        // Cart can be null - cart items can exist without being associated to a cart
        if (cartItem.getCart() != null) {
            dto.setCartId(cartItem.getCart().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private CartItem convertToEntity(CartItemDto dto) {
        CartItem cartItem = new CartItem();
        cartItem.setCantidad(dto.getCantidad());
        // If bookId is provided, fetch and set the book
        if (dto.getBookId() != null) {
            Optional<Book> book = bookService.findById(dto.getBookId());
            if (book.isPresent()) {
                cartItem.setBook(book.get());
            } else {
                // Handle case where book does not exist - could throw exception or log warning
                // For now, we'll allow the cart item to be created without book association
            }
        }
        // If cartId is provided, fetch and set the cart
        if (dto.getCartId() != null) {
            Optional<Cart> cart = cartService.findById(dto.getCartId());
            if (cart.isPresent()) {
                cartItem.setCart(cart.get());
            } else {
                // Handle case where cart does not exist - could throw exception or log warning
                // For now, we'll allow the cart item to be created without cart association
            }
        }
        return cartItem;
    }
}