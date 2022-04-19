package com.pb.ProjetoGrupo2.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "ORDERS")
public class Order {
    // quantity, purchase_date, delivery_date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Timestamp purchase_date;
    private Timestamp delivery_date;

    public Order(Integer quantity, Timestamp purchase_date, Timestamp delivery_date) {
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.delivery_date = delivery_date;
    }
}
