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

import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.catalog.IService.IBookService;
import com.ecommerce.order.Dto.OrderItemDto;
import com.ecommerce.order.Entity.Order;
import com.ecommerce.order.Entity.OrderItem;
import com.ecommerce.order.IService.IOrderItemService;
import com.ecommerce.order.IService.IOrderService;

/**
 * REST Controller for the OrderItem entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final IOrderItemService orderItemService;
    private final IBookService bookService;
    private final IOrderService orderService;

    public OrderItemController(IOrderItemService orderItemService, IBookService bookService, IOrderService orderService) {
        this.orderItemService = orderItemService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        List<OrderItemDto> orderItemDtos = orderItems.stream().map(orderItem -> convertToDto(orderItem)).collect(Collectors.toList());
        return ResponseEntity.ok(orderItemDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemService.findById(id);
        if (orderItem.isPresent()) {
            return ResponseEntity.ok(convertToDto(orderItem.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
        List<OrderItemDto> orderItemDtos = orderItems.stream().map(orderItem -> convertToDto(orderItem)).collect(Collectors.toList());
        return ResponseEntity.ok(orderItemDtos);
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItem orderItem = convertToEntity(orderItemDto);
        OrderItem savedOrderItem = orderItemService.save(orderItem);
        return ResponseEntity.ok(convertToDto(savedOrderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDto orderItemDto) {
        Optional<OrderItem> existingOrderItem = orderItemService.findById(id);
        if (existingOrderItem.isPresent()) {
            OrderItem orderItem = existingOrderItem.get();
            orderItem.setCantidad(orderItemDto.getCantidad());
            orderItem.setPrecioUnitario(orderItemDto.getPrecioUnitario());
            // Update book association if bookId is provided
            if (orderItemDto.getBookId() != null) {
                Optional<Book> book = bookService.findById(orderItemDto.getBookId());
                if (book.isPresent()) {
                    orderItem.setBook(book.get());
                } else {
                    // Handle case where book does not exist
                    orderItem.setBook(null);
                }
            } else {
                // If bookId is null, remove association
                orderItem.setBook(null);
            }
            // Update order association if orderId is provided
            if (orderItemDto.getOrderId() != null) {
                Optional<Order> order = orderService.findById(orderItemDto.getOrderId());
                if (order.isPresent()) {
                    orderItem.setOrder(order.get());
                } else {
                    // Handle case where order does not exist
                    orderItem.setOrder(null);
                }
            } else {
                // If orderId is null, remove association
                orderItem.setOrder(null);
            }
            OrderItem updatedOrderItem = orderItemService.save(orderItem);
            return ResponseEntity.ok(convertToDto(updatedOrderItem));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        if (orderItemService.findById(id).isPresent()) {
            orderItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OrderItemDto convertToDto(OrderItem orderItem) {
        OrderItemDto dto = new OrderItemDto();
        dto.setCantidad(orderItem.getCantidad());
        dto.setPrecioUnitario(orderItem.getPrecioUnitario());
        // Book can be null - order items can exist without being associated to a book
        if (orderItem.getBook() != null) {
            dto.setBookId(orderItem.getBook().getId());
        }
        // Order can be null - order items can exist without being associated to an order
        if (orderItem.getOrder() != null) {
            dto.setOrderId(orderItem.getOrder().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private OrderItem convertToEntity(OrderItemDto dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCantidad(dto.getCantidad());
        orderItem.setPrecioUnitario(dto.getPrecioUnitario());
        // If bookId is provided, fetch and set the book
        if (dto.getBookId() != null) {
            Optional<Book> book = bookService.findById(dto.getBookId());
            if (book.isPresent()) {
                orderItem.setBook(book.get());
            } else {
                // Handle case where book does not exist - could throw exception or log warning
                // For now, we'll allow the order item to be created without book association
            }
        }
        // If orderId is provided, fetch and set the order
        if (dto.getOrderId() != null) {
            Optional<Order> order = orderService.findById(dto.getOrderId());
            if (order.isPresent()) {
                orderItem.setOrder(order.get());
            } else {
                // Handle case where order does not exist - could throw exception or log warning
                // For now, we'll allow the order item to be created without order association
            }
        }
        return orderItem;
    }
}