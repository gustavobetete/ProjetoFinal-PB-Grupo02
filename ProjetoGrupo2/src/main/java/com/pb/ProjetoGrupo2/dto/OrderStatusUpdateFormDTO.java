package com.pb.ProjetoGrupo2.dto;

import com.pb.ProjetoGrupo2.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatusUpdateFormDTO {

    private OrderStatus status;

}
