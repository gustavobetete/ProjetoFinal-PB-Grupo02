package com.pb.ProjetoGrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionDto {

    private Long id;
    //private List<Product> productList;
    private String description;
    private BigDecimal promotion_price;
}
