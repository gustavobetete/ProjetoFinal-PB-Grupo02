package com.pb.ProjetoGrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionFormDto {

    @NotNull(message = "O campo description não pode ser nulo")
    @NotEmpty(message = "O campo Name não pode ser vazio")
    @NotBlank(message = "O campo Name não pode estar em branco")
    private String description;

    @NotNull(message = "O campo promotion_price não pode ser nulo")
    private BigDecimal promotionPrice;

}
