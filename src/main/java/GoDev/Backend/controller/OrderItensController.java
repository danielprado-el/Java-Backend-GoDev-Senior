package GoDev.Backend.controller;

import GoDev.Backend.model.Item;
import GoDev.Backend.model.OrderItens;
import GoDev.Backend.repository.ItemRepository;
import GoDev.Backend.repository.OrderItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderItensController {

    @Autowired
    private OrderItensRepository orderItensRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/orders/{orderId}/items")
    public List<OrderItens> getAllOrderItens(@PathVariable(value = "orderId") UUID orderId) {
        return orderItensRepository.findByOrderId(orderId);
    }

    @GetMapping("/orders/{orderId}/items/{orderItemId}")
    public ResponseEntity<OrderItens> getOrderItensById(@PathVariable(value = "orderId") UUID orderId, @PathVariable(value = "orderItemId") UUID orderItemId) {
        OrderItens orderItens = orderItensRepository.findById(orderItemId);
        if (orderItens.getOrderId().equals(orderId)){
            return ResponseEntity.ok().body(orderItens);
        }
        return ResponseEntity.badRequest().body(orderItens);
    }

    @PostMapping("/orders/{id}/items")
    public OrderItens createOrderItens(@PathVariable(value = "id") UUID orderId, @Valid @RequestBody OrderItens orderItens) {
        Item item = itemRepository.findById(orderItens.getItemId());
        orderItens.setOrderId(orderId);
        orderItens.setTotalValue(item.getValue() * orderItens.getQuantity());
        return orderItensRepository.save(orderItens);
    }

    @PutMapping("/orders/{orderId}/items/{orderItemId}")
    public ResponseEntity<OrderItens> updateOrderItens(@PathVariable(value = "orderId") UUID orderId, @PathVariable(value = "orderItemId") UUID orderItemId, @Valid @RequestBody OrderItens orderItensDetails) {
        OrderItens orderItens = orderItensRepository.findById(orderItemId);
        if (orderItens.getOrderId().equals(orderId)) {
            Item item = itemRepository.findById(orderItensDetails.getItemId());
            orderItens.setItemId(orderItensDetails.getItemId());
            orderItens.setQuantity(orderItensDetails.getQuantity());
            orderItens.setTotalValue(item.getValue() * orderItens.getQuantity());
            final OrderItens updateOrderItens = orderItensRepository.save(orderItens);
            return ResponseEntity.ok(updateOrderItens);
        }
        return ResponseEntity.badRequest().body(orderItensDetails);
    }

    @DeleteMapping("/orders/{orderId}/items/{orderItemId}")
    public void deleteOrderItens(@PathVariable(value = "orderId") UUID orderId, @PathVariable(value = "orderItemId") UUID orderItemId) {
        OrderItens orderItens = orderItensRepository.findById(orderItemId);
        if (orderItens.getOrderId().equals(orderId)) {
            orderItensRepository.delete(orderItens);
        }
    }
}
