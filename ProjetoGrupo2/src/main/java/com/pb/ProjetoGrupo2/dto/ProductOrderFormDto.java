package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderFormDto {

   private Long productId;
   private Long orderId;
}
