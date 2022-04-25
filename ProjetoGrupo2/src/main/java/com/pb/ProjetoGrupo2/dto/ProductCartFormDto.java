package com.pb.ProjetoGrupo2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartFormDto {

   @NotNull(message = "O campo Name não pode ser nulo")
   private Long productId;

   @NotNull(message = "O campo Name não pode ser nulo")
   private Long orderId;
}
