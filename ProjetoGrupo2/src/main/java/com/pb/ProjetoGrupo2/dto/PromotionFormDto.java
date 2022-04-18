package com.pb.ProjetoGrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionFormDto {

//    @NotNull(message = "A lista productList não pode ser nula")
//    @NotEmpty(message = "A lista productList não pode ser vazia")
//    @NotBlank(message = "A lista productList não pode estar em branco")
//    private List<Product> productList;

    @NotNull(message = "O campo description não pode ser nulo")
    private String description;

    @NotNull(message = "O campo promotion_price não pode ser nulo")
    private BigDecimal promotionPrice;
}
