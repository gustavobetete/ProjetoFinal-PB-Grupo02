package com.pb.ProjetoGrupo2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Integer quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a", locale = "pt-BR")
    private Timestamp purchaseDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss a", locale = "pt-BR")
    private Timestamp deliveryDate;
    private Long idUser;
    private List<Product> products;
}
