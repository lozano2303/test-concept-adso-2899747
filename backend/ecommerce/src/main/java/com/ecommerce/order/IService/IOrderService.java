package com.ecommerce.order.IService;

import java.util.List;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.order.Entity.Order;

/**
 * Interfaz de servicio para la entidad Order.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface IOrderService extends IBaseService<Order, Long> {

    /**
     * Busca todas las órdenes de un usuario específico.
     * @param userId El ID del usuario.
     * @return Una lista de órdenes del usuario.
     */
    List<Order> findByUserId(Long userId);
}