package com.ecommerce.order.IRepository;

import java.util.List;

import com.ecommerce.common.IRepository.IBaseRepository;
import com.ecommerce.order.Entity.Order;

/**
 * Interfaz de repositorio para la entidad Order.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IOrderRepository extends IBaseRepository<Order, Long> {

    /**
     * Busca todas las órdenes de un usuario específico.
     * @param userId El ID del usuario.
     * @return Una lista de órdenes del usuario.
     */
    List<Order> findByUserId(Long userId);
}