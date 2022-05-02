package com.pb.ProjetoGrupo2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pb.ProjetoGrupo2.constants.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.ZonedDateTime.now;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR")
    private LocalDateTime localDate;
    private UserOrderDTO user;
    private BigDecimal totalPrice;
    private OrderStatus status;

}
