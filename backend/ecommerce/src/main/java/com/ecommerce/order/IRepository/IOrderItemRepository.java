package com.ecommerce.order.IRepository;

import java.util.List;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.order.Entity.OrderItem;

/**
 * Interfaz de repositorio para la entidad OrderItem.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IOrderItemRepository extends IBaseRepository<OrderItem, Long> {

    /**
     * Busca todos los items de una orden específica.
     * @param orderId El ID de la orden.
     * @return Una lista de items de la orden.
     */
    List<OrderItem> findByOrderId(Long orderId);
}