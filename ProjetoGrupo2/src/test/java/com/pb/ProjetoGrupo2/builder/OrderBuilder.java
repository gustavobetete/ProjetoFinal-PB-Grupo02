//package com.pb.ProjetoGrupo2.builder;
//
//import com.pb.ProjetoGrupo2.dto.OrderDto;
//import com.pb.ProjetoGrupo2.dto.OrderFormDto;
//import com.pb.ProjetoGrupo2.entities.Order;
//import com.pb.ProjetoGrupo2.entities.Product;
//
//import java.sql.Timestamp;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderBuilder {
//
//    public static Order getOrder() {
//        Order order = new Order();
//        order.setId(1L);
//        order.setQuantity(1);
//        order.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
//        order.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
//        order.setUser(UserBuilder.getUser());
//        return order;
//    }
//
//    public static OrderDto getOrderDto() {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(1L);
//        orderDto.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
//        orderDto.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
//        orderDto.setIdUser(UserBuilder.getUser().getId());
//        return orderDto;
//    }
//
//    public static OrderDto getOrderDtoTwo() {
//        OrderDto orderDtoTwo = new OrderDto();
//        orderDtoTwo.setId(2L);
//        orderDtoTwo.setPurchaseDate(ZonedDateTime.now(ZoneId.systemDefault()));
//        orderDtoTwo.setDeliveryDate(ZonedDateTime.now().withHour(22).withMinute(0).withSecond(0));
//        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
//        return orderDtoTwo;
//    }
//
//    public static OrderFormDto getOrderFormDto() {
//        OrderFormDto orderFormDto = new OrderFormDto();
//        orderFormDto.setIdUser(UserBuilder.getUser().getId());
//        return orderFormDto;
//    }
//}
