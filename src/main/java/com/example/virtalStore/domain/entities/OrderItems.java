package com.example.virtalStore.domain.entities;

import com.example.virtalStore.domain.entities.pk.OrderItemsPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@Entity
@Table(name = "tb_orderitems")
public class OrderItems implements Serializable {
    private static final long serialVersionUID =1L;

    @EmbeddedId
    private OrderItemsPK id = new OrderItemsPK();
    private Integer quantity;

    private Double price;

    public OrderItems(Order order,Product product, Integer quantity, Double price){
        id.setOrder(order);
        id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }

    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }

    public void setOrder(Order order){
        id.setOrder(order);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal(){
        return quantity * price;
    }

}
