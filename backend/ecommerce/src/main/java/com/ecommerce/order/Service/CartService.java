package com.ecommerce.order.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.order.Entity.Cart;
import com.ecommerce.order.IRepository.ICartRepository;
import com.ecommerce.order.IService.ICartService;

/**
 * Implementación del servicio para la entidad Cart.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class CartService extends ABaseService<Cart, Long> implements ICartService {

    private final ICartRepository cartRepository;

    public CartService(ICartRepository cartRepository) {
        super(cartRepository);
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}