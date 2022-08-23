package GoDev.Backend.controller;

import GoDev.Backend.model.Item;
import GoDev.Backend.model.Order;
import GoDev.Backend.model.OrderDTO;
import GoDev.Backend.model.OrderItens;
import GoDev.Backend.repository.ItemRepository;
import GoDev.Backend.repository.OrderItensRepository;
import GoDev.Backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItensRepository orderItensRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") UUID orderId) {
        Order order = orderRepository.findById(orderId);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PostMapping("/orders/{id}/close")
    public OrderDTO closeOrder(@PathVariable(value = "id") UUID orderId, @Valid @RequestBody Order orderDetails) {
        List<OrderItens> orderItens = orderItensRepository.findByOrderId(orderId);
        List<Item> Itens = itemRepository.findAll();
        Order order = orderRepository.findById(orderId);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setNumber(order.getNumber());
        orderDTO.setDate(order.getDate());
        orderDTO.setPercentualDiscount(orderDetails.getPercentualDiscount());
        orderDTO.setItems(orderItens);
        orderDTO.setTotalValue(orderDTO.getTotal(orderItens, Itens));
        return orderDTO;
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") UUID orderId, @Valid @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(orderId);
        order.setNumber(orderDetails.getNumber());
        order.setDate(orderDetails.getDate());
        order.setPercentualDiscount(orderDetails.getPercentualDiscount());
        order.setTotalValue(orderDetails.getTotalValue());
        final Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable(value = "id") UUID orderId) {
        Order order = orderRepository.findById(orderId);
        orderRepository.delete(order);
    }


}
