package com.ecommerce.order.IService;

import java.util.Optional;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.order.Entity.Cart;

/**
 * Interfaz de servicio para la entidad Cart.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface ICartService extends IBaseService<Cart, Long> {

    /**
     * Busca el carrito de un usuario específico.
     * @param userId El ID del usuario.
     * @return Un Optional con el carrito del usuario si existe.
     */
    Optional<Cart> findByUserId(Long userId);
}