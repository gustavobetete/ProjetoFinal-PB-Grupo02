package com.pb.ProjetoGrupo2.entities;

import com.pb.ProjetoGrupo2.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private LocalDateTime purchaseDate = LocalDateTime.now();
//    private LocalDateTime deliveryDate = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0).withNano(0);

    private Integer quantityProductInOrder;

//  @ManyToMany(fetch = FetchType.LAZY)
//  @JoinTable(name = "orders_product", joinColumns = @JoinColumn(name = "orders_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
//  private List<Product> products;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate purchaseDate;
    private BigDecimal totalPrice = new BigDecimal(0);
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.OPEN;

    public Order(LocalDate localDate, User user){
        this.purchaseDate = localDate;
        this.user = user;
    }

}
