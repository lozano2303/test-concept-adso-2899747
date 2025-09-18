package com.ecommerce.order.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.order.Entity.CartItem;
import com.ecommerce.order.IRepository.ICartItemRepository;
import com.ecommerce.order.IService.ICartItemService;

/**
 * Implementación del servicio para la entidad CartItem.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class CartItemService extends ABaseService<CartItem, Long> implements ICartItemService {

    private final ICartItemRepository cartItemRepository;

    public CartItemService(ICartItemRepository cartItemRepository) {
        super(cartItemRepository);
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}