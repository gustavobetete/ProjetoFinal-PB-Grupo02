package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.CartFormDto;
import com.pb.ProjetoGrupo2.entities.Cart;

import java.sql.Timestamp;

public class CartBuilder {

    public static Cart getOrder() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setQuantity(1);
        cart.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        cart.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        cart.setUser(UserBuilder.getUser());
        return cart;
    }

    public static CartDto getOrderDto() {
        CartDto cartDto = new CartDto();
        cartDto.setId(1L);
        cartDto.setQuantity(1);
        cartDto.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        cartDto.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        cartDto.setIdUser(UserBuilder.getUser().getId());
        return cartDto;
    }

    public static CartDto getOrderDtoTwo() {
        CartDto cartDtoTwo = new CartDto();
        cartDtoTwo.setId(2L);
        cartDtoTwo.setQuantity(2);
        cartDtoTwo.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        cartDtoTwo.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        cartDtoTwo.setIdUser(UserBuilder.getUser().getId());
        return cartDtoTwo;
    }

    public static CartFormDto getOrderFormDto() {
        CartFormDto cartFormDto = new CartFormDto();
        cartFormDto.setDeliveryDate(Timestamp.valueOf("2022-05-23 22:00:00"));
        cartFormDto.setPurchaseDate(Timestamp.valueOf("2022-05-23 12:41:00"));
        cartFormDto.setQuantity(1);
        cartFormDto.setIdUser(UserBuilder.getUser().getId());
        return cartFormDto;
    }
}
