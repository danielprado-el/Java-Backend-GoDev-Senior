package GoDev.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "OrdersItens")
public class OrderItens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @JsonIgnore
    private UUID orderId;
    private UUID itemId;
    private Double quantity;
    private Double totalValue = 0.0;

    public OrderItens() {

    }

    public OrderItens(UUID orderId, UUID itemId, Double quantity, Double totalValue) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public UUID getId() {
        return id;
    }

    @Column(name = "order", nullable = false)
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    @Column(name = "itemId", nullable = false)
    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    @Column(name = "quantity", nullable = false)
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "totalValue", nullable = false)
    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", itemId:" + itemId +
                ", quantity:" + quantity +
                ", totalValue:" + totalValue +
                '}';
    }


}
