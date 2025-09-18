package com.ecommerce.order.IRepository;

import java.util.Optional;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.order.Entity.Cart;

/**
 * Interfaz de repositorio para la entidad Cart.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface ICartRepository extends IBaseRepository<Cart, Long> {

    /**
     * Busca el carrito de un usuario específico.
     * @param userId El ID del usuario.
     * @return Un Optional con el carrito del usuario si existe.
     */
    Optional<Cart> findByUserId(Long userId);
}