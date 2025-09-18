package com.ecommerce.order.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.order.Entity.OrderItem;
import com.ecommerce.order.IRepository.IOrderItemRepository;
import com.ecommerce.order.IService.IOrderItemService;

/**
 * Implementación del servicio para la entidad OrderItem.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class OrderItemService extends ABaseService<OrderItem, Long> implements IOrderItemService {

    private final IOrderItemRepository orderItemRepository;

    public OrderItemService(IOrderItemRepository orderItemRepository) {
        super(orderItemRepository);
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}