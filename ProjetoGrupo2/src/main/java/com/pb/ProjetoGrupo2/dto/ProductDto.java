package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.constants.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private Type type;
    private BigDecimal unitPrice;
    private Integer quantity;

    public ProductDto(String name, Type type, BigDecimal unitPrice, Integer quantity) {
        this.name = name;
        this.type = type;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }
}
