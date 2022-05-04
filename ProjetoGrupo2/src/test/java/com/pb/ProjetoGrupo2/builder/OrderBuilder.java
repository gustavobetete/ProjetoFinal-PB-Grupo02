package com.pb.ProjetoGrupo2.builder;//package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.OrderStatus;
import com.pb.ProjetoGrupo2.dto.OrderDTO;
import com.pb.ProjetoGrupo2.dto.OrderFormDTO;
import com.pb.ProjetoGrupo2.dto.OrderStatusUpdateFormDTO;
import com.pb.ProjetoGrupo2.entities.Order;


import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderBuilder {

      public static Order getOrder(){
          Order order = new Order();
          order.setId(1L);
          order.setUser(UserBuilder.getUser());
          order.setPurchaseDate(LocalDate.now());
          order.setTotalPrice(new BigDecimal(7));
          return order;
      }

    public static OrderDTO getOrderDTO() {
          OrderDTO orderDTO = new OrderDTO();
          orderDTO.setId(1L);
          orderDTO.setLocalDate(LocalDate.now());
          orderDTO.setUser(UserBuilder.getUserOrderDTO());
          orderDTO.setTotalPrice(new BigDecimal(7));
          orderDTO.setStatus(OrderStatus.OPEN);
          return orderDTO;
    }

    public static OrderDTO getOrderDTOTwo() {
        OrderDTO orderDTOTwo = new OrderDTO();
        orderDTOTwo.setId(2L);
        orderDTOTwo.setLocalDate(LocalDate.now());
        orderDTOTwo.setUser(UserBuilder.getUserOrderDTO());
        orderDTOTwo.setTotalPrice(new BigDecimal(7));
        orderDTOTwo.setStatus(OrderStatus.OPEN);
        return orderDTOTwo;
    }

    public static OrderFormDTO getOrderFormDTO() {
        OrderFormDTO orderFormDTO = new OrderFormDTO();
        orderFormDTO.setUserId(2L);
        return orderFormDTO;
    }

    public static OrderStatusUpdateFormDTO getOrderStatusUpdateFormDTO() {
        OrderStatusUpdateFormDTO orderStatusUpdateFormDTO = new OrderStatusUpdateFormDTO();
        orderStatusUpdateFormDTO.setStatus(OrderStatus.OPEN);
          return orderStatusUpdateFormDTO;
    }
}

