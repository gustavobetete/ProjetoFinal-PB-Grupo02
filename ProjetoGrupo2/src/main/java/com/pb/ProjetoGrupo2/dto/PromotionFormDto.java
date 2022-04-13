package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionFormDto {

//    @NotNull(message = "A lista productList não pode ser nula")
//    @NotEmpty(message = "A lista productList não pode ser vazia")
//    @NotBlank(message = "A lista productList não pode estar em branco")
//    private List<Product> productList;

    @NotNull(message = "O campo description não pode ser nulo")
    private String description;

    @NotNull(message = "O campo promotion_price não pode ser nulo")
    private BigDecimal promotion_price;
}
