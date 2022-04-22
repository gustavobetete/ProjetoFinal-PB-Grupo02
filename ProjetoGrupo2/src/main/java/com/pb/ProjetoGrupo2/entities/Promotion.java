package com.pb.ProjetoGrupo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal promotionPrice;

    @ManyToMany//(mappedBy = "promotion")
    @JoinTable(name = "promotion_id", joinColumns = @JoinColumn(name = "promotion_product"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> product;
}
