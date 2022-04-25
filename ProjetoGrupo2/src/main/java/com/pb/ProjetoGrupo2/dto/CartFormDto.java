package com.pb.ProjetoGrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartFormDto {

    @NotNull(message = "O campo Quantity não pode ser nulo")
    private Integer quantity;

    @NotNull(message = "O campo purchase_date não pode ser nulo")
    private Timestamp purchaseDate;

    @NotNull(message = "O campo delivery_date não pode ser nulo")
    private Timestamp deliveryDate;

    private Long idUser;
}
