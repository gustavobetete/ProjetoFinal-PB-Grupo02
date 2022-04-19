package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.entities.Order;

import java.sql.Timestamp;

public class OrderBuilder {
    public static Order getOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(1);
        order.setPurchase_date(Timestamp.valueOf("2022-05-23 12:41:00"));
        order.setDelivery_date(Timestamp.valueOf("2022-05-23 22:00:00"));
        return order;
    }

    public static OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setQuantity(1);
        orderDto.setPurchase_date(Timestamp.valueOf("2022-05-23 12:41:00"));
        orderDto.setDelivery_date(Timestamp.valueOf("2022-05-23 22:00:00"));
        return orderDto;
    }

    public static OrderDto getOrderDtoTwo() {
        OrderDto orderDtoTwo = new OrderDto();
        orderDtoTwo.setId(2L);
        orderDtoTwo.setQuantity(2);
        orderDtoTwo.setPurchase_date(Timestamp.valueOf("2022-05-01 16:23:00"));
        orderDtoTwo.setDelivery_date(Timestamp.valueOf("2022-05-01 22:00:00"));
        return orderDtoTwo;
    }
}
