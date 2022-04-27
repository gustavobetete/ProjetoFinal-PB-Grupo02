package com.pb.ProjetoGrupo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
<<<<<<< HEAD
    private ZonedDateTime purchaseDate = now(ZoneId.systemDefault());
    private ZonedDateTime deliveryDate = now().withHour(22).withMinute(0).withSecond(0);
=======
    private LocalDateTime purchaseDate = LocalDateTime.now();
    private LocalDateTime deliveryDate = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0).withNano(0);
>>>>>>> dev-gustavo

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_product", joinColumns = @JoinColumn(name = "orders_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double total;
    private BigDecimal productTotal;

}
