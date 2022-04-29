//package com.pb.ProjetoGrupo2.builder;
//
//import com.pb.ProjetoGrupo2.dto.OrderDto;
//import com.pb.ProjetoGrupo2.dto.OrderFormDto;
//import com.pb.ProjetoGrupo2.entities.Order;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//public class OrderBuilder {
//
//    public static Order getOrder() {
//        Order order = new Order();
//        order.setId(1L);
//        order.setQuantity(1);
//        order.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
//        order.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
//        order.setUser(UserBuilder.getUser());
//        order.setProducts(ProductBuilder.getProducts());
//        return order;
//    }

//    public static OrderDto getOrderDto() {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(1L);
//        orderDto.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
//        orderDto.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
//        orderDto.setIdUser(UserBuilder.getUser().getId());
//        orderDto.setProducts(ProductBuilder.getProducts());
//        return orderDto;
//    }
//
//    public static OrderDto getOrderDtoTwo() {
//        OrderDto orderDtoTwo = new OrderDto();
//        orderDtoTwo.setId(2L);
//        orderDtoTwo.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
//        orderDtoTwo.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
//        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
//        orderDtoTwo.setProducts(ProductBuilder.getProducts());
//        return orderDtoTwo;
//    }

//    public static OrderFormDto getOrderFormDto() {
//        OrderFormDto orderFormDto = new OrderFormDto();
//        orderFormDto.setIdUser(UserBuilder.getUser().getId());
//        orderFormDto.setProducts(Arrays.asList(ProductBuilder.getProductOrderFormDto()));
//
//        return orderFormDto;
//    }
//}

