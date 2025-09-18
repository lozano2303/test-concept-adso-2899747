package com.ecommerce.order.IRepository;

import java.util.List;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.order.Entity.CartItem;

/**
 * Interfaz de repositorio para la entidad CartItem.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface ICartItemRepository extends IBaseRepository<CartItem, Long> {

    /**
     * Busca todos los items de un carrito específico.
     * @param cartId El ID del carrito.
     * @return Una lista de items del carrito.
     */
    List<CartItem> findByCartId(Long cartId);
}