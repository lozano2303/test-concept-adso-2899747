package com.ecommerce.order.IService;

import java.util.List;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.order.Entity.CartItem;

/**
 * Interfaz de servicio para la entidad CartItem.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface ICartItemService extends IBaseService<CartItem, Long> {

    /**
     * Busca todos los items de un carrito específico.
     * @param cartId El ID del carrito.
     * @return Una lista de items del carrito.
     */
    List<CartItem> findByCartId(Long cartId);
}