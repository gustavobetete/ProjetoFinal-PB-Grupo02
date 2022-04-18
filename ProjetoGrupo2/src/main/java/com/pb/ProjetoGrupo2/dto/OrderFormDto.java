package com.pb.ProjetoGrupo2.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class OrderFormDto {

    @NotNull(message = "O campo Quantity não pode ser nulo")
    private Integer quantity;

    @NotNull(message = "O campo purchase_date não pode ser nulo")
    private Timestamp purchase_date;

    @NotNull(message = "O campo delivery_date não pode ser nulo")
    private Timestamp delivery_date;
}
