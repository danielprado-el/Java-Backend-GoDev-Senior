package GoDev.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Integer number;
    private Timestamp date;
    private Double percentualDiscount;
    private Double totalValue = 0.0;
    @JsonIgnore
    @OneToMany(targetEntity=OrderItens.class, fetch= FetchType.EAGER)
    private List<OrderItens> items;

    public Order(){

    }

    public Order(Integer number, Timestamp date, Double percentualDiscount, Double totalValue) {
        this.number = number;
        this.date = date;
        this.percentualDiscount = percentualDiscount;
        this.totalValue = totalValue;
    }

    public UUID getId() {
        return id;
    }

    @Column(name = "number", nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Column(name = "percentualDiscount")
    public Double getPercentualDiscount() {
        return percentualDiscount;
    }

    public void setPercentualDiscount(Double percentualDiscount) {
        this.percentualDiscount = percentualDiscount;
    }

    @Column(name = "totalValue", nullable = false)
    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public List<OrderItens> getItems() {
        return items;
    }

    public void setItems(List<OrderItens> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", percentualDiscount=" + percentualDiscount +
                ", totalValue=" + totalValue +
                '}';
    }
}
