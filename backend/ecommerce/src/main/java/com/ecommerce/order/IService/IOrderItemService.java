package com.ecommerce.order.IService;

import java.util.List;

import com.ecommerce.common.IService.IBaseService;
import com.ecommerce.order.Entity.OrderItem;

/**
 * Interfaz de servicio para la entidad OrderItem.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface IOrderItemService extends IBaseService<OrderItem, Long> {

    /**
     * Busca todos los items de una orden específica.
     * @param orderId El ID de la orden.
     * @return Una lista de items de la orden.
     */
    List<OrderItem> findByOrderId(Long orderId);
}