package com.pb.ProjetoGrupo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    // quantity, purchase_date, delivery_date

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private LocalDate purchase_date;
    private LocalDate delivery_date;

    public Order(Integer quantity, LocalDate purchase_date, LocalDate delivery_date) {
        this.quantity = quantity;
        this.purchase_date = purchase_date;
        this.delivery_date = delivery_date;
    }
}
