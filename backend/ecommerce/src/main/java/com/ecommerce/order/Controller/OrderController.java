package com.ecommerce.order.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.Dto.OrderDto;
import com.ecommerce.order.Entity.Order;
import com.ecommerce.order.IService.IOrderService;
import com.ecommerce.user.Entity.User;
import com.ecommerce.user.IService.IUserService;

/**
 * REST Controller for the Order entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final IOrderService orderService;
    private final IUserService userService;

    public OrderController(IOrderService orderService, IUserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        List<OrderDto> orderDtos = orders.stream().map(order -> convertToDto(order)).collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(convertToDto(order.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findByUserId(userId);
        List<OrderDto> orderDtos = orders.stream().map(order -> convertToDto(order)).collect(Collectors.toList());
        return ResponseEntity.ok(orderDtos);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        Order savedOrder = orderService.save(order);
        return ResponseEntity.ok(convertToDto(savedOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        Optional<Order> existingOrder = orderService.findById(id);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setFecha(orderDto.getFecha());
            order.setTotal(orderDto.getTotal());
            order.setEstado(orderDto.getEstado());
            // Update user association if userId is provided
            if (orderDto.getUserId() != null) {
                Optional<User> user = userService.findById(orderDto.getUserId());
                if (user.isPresent()) {
                    order.setUser(user.get());
                } else {
                    // Handle case where user does not exist
                    order.setUser(null);
                }
            } else {
                // If userId is null, remove association
                order.setUser(null);
            }
            Order updatedOrder = orderService.save(order);
            return ResponseEntity.ok(convertToDto(updatedOrder));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderService.findById(id).isPresent()) {
            orderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setFecha(order.getFecha());
        dto.setTotal(order.getTotal());
        dto.setEstado(order.getEstado());
        // User can be null - orders can exist without being associated to a user
        if (order.getUser() != null) {
            dto.setUserId(order.getUser().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private Order convertToEntity(OrderDto dto) {
        Order order = new Order();
        order.setFecha(dto.getFecha());
        order.setTotal(dto.getTotal());
        order.setEstado(dto.getEstado());
        // If userId is provided, fetch and set the user
        if (dto.getUserId() != null) {
            Optional<User> user = userService.findById(dto.getUserId());
            if (user.isPresent()) {
                order.setUser(user.get());
            } else {
                // Handle case where user does not exist - could throw exception or log warning
                // For now, we'll allow the order to be created without user association
            }
        }
        return order;
    }
}