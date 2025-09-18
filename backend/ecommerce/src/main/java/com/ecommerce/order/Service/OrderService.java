package com.ecommerce.order.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.order.Entity.Order;
import com.ecommerce.order.IRepository.IOrderRepository;
import com.ecommerce.order.IService.IOrderService;

/**
 * Implementación del servicio para la entidad Order.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class OrderService extends ABaseService<Order, Long> implements IOrderService {

    private final IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}