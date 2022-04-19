package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.constants.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFormDto {

    @NotNull(message = "O campo Name não pode ser nulo")
    @NotEmpty(message = "O campo Name não pode ser vazio")
    @NotBlank(message = "O campo Name não pode estar em branco")
    private String name;

    @NotNull(message = "O campo type não pode ser nulo")
    private Type type;

    @NotNull(message = "O campo unitPrice não pode ser nulo")
    private BigDecimal unit_price;

    @NotNull(message = "O campo quantity não pode ser nulo")
    private Integer quantity;
}
