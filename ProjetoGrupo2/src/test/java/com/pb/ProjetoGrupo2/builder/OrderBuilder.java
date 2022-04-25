package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;

import java.sql.Timestamp;

public class OrderBuilder {

    public static Order getOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(1);
        order.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        order.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        order.setUser(UserBuilder.getUser());
        return order;
    }

    public static OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setQuantity(1);
        orderDto.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        orderDto.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        orderDto.setIdUser(UserBuilder.getUser().getId());
        return orderDto;
    }

    public static OrderDto getOrderDtoTwo() {
        OrderDto orderDtoTwo = new OrderDto();
        orderDtoTwo.setId(2L);
        orderDtoTwo.setQuantity(2);
        orderDtoTwo.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        orderDtoTwo.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
        return orderDtoTwo;
    }

    public static OrderFormDto getOrderFormDto() {
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        orderFormDto.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        orderFormDto.setQuantity(1);
        orderFormDto.setIdUser(UserBuilder.getUser().getId());
        return orderFormDto;
    }
}
