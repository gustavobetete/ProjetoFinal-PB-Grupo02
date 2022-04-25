package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderFormDto {

   @NotNull(message = "O campo Name não pode ser nulo")
   private Long productId;

   @NotNull(message = "O campo Name não pode ser nulo")
   private Long orderId;
}
