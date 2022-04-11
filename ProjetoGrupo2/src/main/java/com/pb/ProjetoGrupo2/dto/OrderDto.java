package com.pb.ProjetoGrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private Integer quantity;
    private LocalDate purchase_date;
    private LocalDate delivery_date;
}
