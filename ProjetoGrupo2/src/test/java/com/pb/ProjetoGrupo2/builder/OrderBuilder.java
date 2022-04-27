package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
<<<<<<< HEAD
import com.pb.ProjetoGrupo2.entities.Product;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
=======

import java.time.LocalDateTime;
import java.util.Arrays;

>>>>>>> dev-gustavo
public class OrderBuilder {

    public static Order getOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(1);
<<<<<<< HEAD
        order.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
        order.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
        order.setUser(UserBuilder.getUser());
=======
        order.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
        order.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
        order.setUser(UserBuilder.getUser());
        order.setProducts(ProductBuilder.getProducts());
>>>>>>> dev-gustavo
        return order;
    }

    public static OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
<<<<<<< HEAD
        orderDto.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
        orderDto.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
        orderDto.setIdUser(UserBuilder.getUser().getId());
=======
        orderDto.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
        orderDto.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
        orderDto.setIdUser(UserBuilder.getUser().getId());
        orderDto.setProducts(ProductBuilder.getProducts());
>>>>>>> dev-gustavo
        return orderDto;
    }

    public static OrderDto getOrderDtoTwo() {
        OrderDto orderDtoTwo = new OrderDto();
        orderDtoTwo.setId(2L);
<<<<<<< HEAD
        orderDtoTwo.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
        orderDtoTwo.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
=======
        orderDtoTwo.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
        orderDtoTwo.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
        orderDtoTwo.setProducts(ProductBuilder.getProducts());
>>>>>>> dev-gustavo
        return orderDtoTwo;
    }

    public static OrderFormDto getOrderFormDto() {
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setIdUser(UserBuilder.getUser().getId());
<<<<<<< HEAD
=======
        orderFormDto.setProducts(Arrays.asList(ProductBuilder.getProductOrderFormDto()));

>>>>>>> dev-gustavo
        return orderFormDto;
    }
}
