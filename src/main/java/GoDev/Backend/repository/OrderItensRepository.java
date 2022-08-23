package GoDev.Backend.repository;

import GoDev.Backend.model.Item;
import GoDev.Backend.model.OrderItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderItensRepository extends JpaRepository<OrderItens, Long> {
    OrderItens findById(UUID orderItemId);

    List<OrderItens> findByOrderId(@Param("orderId") UUID orderId);

    List<OrderItens> findByItemId(@Param("itemId") UUID itemId);
}
