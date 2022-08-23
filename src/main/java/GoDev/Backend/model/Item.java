package GoDev.Backend.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;
    private Double value;
    private char type;

    public Item(){

    }

    public Item(String description, Double value, char type) {
        this.description = description;
        this.value = value;
        if (type == 'S' || type == 'P') {
            this.type = type;
        }
    }

    public UUID getId() {
        return id;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "value", nullable = false)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = "type", nullable = false)
    public char getType() {
        return type;
    }

    public void setType(char type) {
        if (type == 'S' || type == 'P') {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", type=" + type +
                '}';
    }
}
