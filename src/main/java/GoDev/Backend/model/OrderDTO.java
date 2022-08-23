package GoDev.Backend.model;

import GoDev.Backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class OrderDTO {

    @Autowired
    private ItemRepository itemRepository;

    private UUID id;
    private Integer number;
    private Timestamp date;
    private Double percentualDiscount;
    private Double totalValue = 0.0;
    private List<OrderItens> items;

    public OrderDTO(){

    }

    public OrderDTO(UUID id, Integer number, Timestamp date, Double percentualDiscount, Double totalValue) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.percentualDiscount = percentualDiscount;
        this.totalValue = totalValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Double getPercentualDiscount() {
        return percentualDiscount;
    }

    public void setPercentualDiscount(Double percentualDiscount) {
        this.percentualDiscount = percentualDiscount;
    }

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

    public Double calcTotalServices(List<OrderItens> list, List<Item> items){
        Double total = 0.0;
        for(int i = 0; i < list.size(); i++) {
            for(int z = 0; z < items.size(); z++) {
                if (list.get(i).getItemId().equals(items.get(z).getId()) && items.get(z).getType() == 'S'){
                    total += list.get(i).getTotalValue();
                }
            }
        }
        return total;
    }

    public Double getTotalProduts(List<OrderItens> list, List<Item> items){
        Double total = 0.0;
        for(int i = 0; i < list.size(); i++) {
            for(int z = 0; z < items.size(); z++) {
                if (list.get(i).getItemId().equals(items.get(z).getId()) && items.get(z).getType() == 'P'){
                    total += list.get(i).getTotalValue();
                }
            }
        }
        return (total - (this.percentualDiscount/100) * total);
    }

    public Double getTotal(List<OrderItens> list, List<Item> items){
        Double produts = getTotalProduts(list, items);
        Double services = calcTotalServices(list, items);

        return produts + services;
    }
}
