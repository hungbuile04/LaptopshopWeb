package vn.hungbuishop.laptopshop.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private long id;
    //long orderId;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    //private long productId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private long quantity;
    private double price;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
