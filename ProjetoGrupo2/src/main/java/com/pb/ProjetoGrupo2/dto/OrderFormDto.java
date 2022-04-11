package com.pb.ProjetoGrupo2.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public class OrderFormDto {

    @NotNull(message = "O campo Quantity não pode ser nulo")
    @NotEmpty(message = "O campo Quantity não pode ser vazio")
    @NotBlank(message = "O campo Quantity não pode estar em branco")
    private Integer quantity;

    @NotNull(message = "O campo purchase_date não pode ser nulo")
    private LocalDate purchase_date;

    @NotNull(message = "O campo delivery_date não pode ser nulo")
    private LocalDate delivery_date;
}
