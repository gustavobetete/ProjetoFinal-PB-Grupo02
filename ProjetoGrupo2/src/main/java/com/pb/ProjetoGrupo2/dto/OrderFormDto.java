package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormDto {

    private Long idUser;

    private List<ProductOrderFormDto> products;

}
