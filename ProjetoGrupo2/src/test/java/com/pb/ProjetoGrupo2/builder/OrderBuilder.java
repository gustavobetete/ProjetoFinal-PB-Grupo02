package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
<<<<<<< HEAD
<<<<<<< HEAD
import com.pb.ProjetoGrupo2.entities.Product;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
=======
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order

import java.time.LocalDateTime;
import java.util.Arrays;

<<<<<<< HEAD
>>>>>>> dev-gustavo
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
public class OrderBuilder {

    public static Order getOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setQuantity(1);
<<<<<<< HEAD
<<<<<<< HEAD
        order.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
        order.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
        order.setUser(UserBuilder.getUser());
=======
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        order.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
        order.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
        order.setUser(UserBuilder.getUser());
        order.setProducts(ProductBuilder.getProducts());
<<<<<<< HEAD
>>>>>>> dev-gustavo
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        return order;
    }

    public static OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
<<<<<<< HEAD
<<<<<<< HEAD
        orderDto.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
        orderDto.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
        orderDto.setIdUser(UserBuilder.getUser().getId());
=======
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        orderDto.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
        orderDto.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
        orderDto.setIdUser(UserBuilder.getUser().getId());
        orderDto.setProducts(ProductBuilder.getProducts());
<<<<<<< HEAD
>>>>>>> dev-gustavo
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        return orderDto;
    }

    public static OrderDto getOrderDtoTwo() {
        OrderDto orderDtoTwo = new OrderDto();
        orderDtoTwo.setId(2L);
<<<<<<< HEAD
<<<<<<< HEAD
        orderDtoTwo.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
        orderDtoTwo.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
=======
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        orderDtoTwo.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
        orderDtoTwo.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
        orderDtoTwo.setProducts(ProductBuilder.getProducts());
<<<<<<< HEAD
>>>>>>> dev-gustavo
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        return orderDtoTwo;
    }

    public static OrderFormDto getOrderFormDto() {
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setIdUser(UserBuilder.getUser().getId());
<<<<<<< HEAD
<<<<<<< HEAD
=======
        orderFormDto.setProducts(Arrays.asList(ProductBuilder.getProductOrderFormDto()));

>>>>>>> dev-gustavo
=======
        orderFormDto.setProducts(Arrays.asList(ProductBuilder.getProductOrderFormDto()));

>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        return orderFormDto;
    }
}
