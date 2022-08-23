package GoDev.Backend.repository;

import GoDev.Backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(UUID orderId);

}
