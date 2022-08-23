package GoDev.Backend.repository;

import GoDev.Backend.model.Item;
import GoDev.Backend.model.OrderItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findById(UUID itemId);

    List<Item> findByType(@Param("type") char type);
}
