package com.pb.ProjetoGrupo2.entities;

import com.pb.ProjetoGrupo2.constants.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    // name, type, unit_price, quantity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private BigDecimal unit_price;
    private Integer quantity;

}
