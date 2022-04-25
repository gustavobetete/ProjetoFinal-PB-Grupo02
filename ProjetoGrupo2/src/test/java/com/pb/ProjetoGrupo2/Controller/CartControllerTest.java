package com.pb.ProjetoGrupo2.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.builder.CartBuilder;
import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.CartFormDto;
import com.pb.ProjetoGrupo2.entities.Cart;
import com.pb.ProjetoGrupo2.repository.CartRepository;
import com.pb.ProjetoGrupo2.service.CartService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void postOrder() throws Exception{

        Cart cart = CartBuilder.getOrder();
        CartDto cartDto = CartBuilder.getOrderDto();

        when(cartService.save(any())).thenReturn(cartDto);

        mockMvc.perform(post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cart)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.quantity").value(cart.getQuantity()))
                .andDo(print());

    }

    @Test
    void getOrder() throws Exception{

        List<CartDto> cartDtoList = new ArrayList<>(
                Arrays.asList(CartBuilder.getOrderDto(), CartBuilder.getOrderDtoTwo())
        );

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<CartDto> productDtoPage = new PageImpl<>(cartDtoList, pageRequest, cartDtoList.size());

        when(cartService.findAll(any(PageRequest.class))).thenReturn(productDtoPage);

        mockMvc.perform(get("/cart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].quantity").value(1))
                .andExpect(jsonPath("$.content.[1].quantity").value(2))
                .andDo(print());

    }

    @Test
    void getOrderById() throws Exception{

        Cart cart = CartBuilder.getOrder();
        CartDto cartDto = CartBuilder.getOrderDto();

        when(cartService.findById(cart.getId())).thenReturn(cartDto);

        long id = 1;

        mockMvc.perform(get("/cart/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(cart.getQuantity()))
                .andDo(print());

    }

    @Test
    void updateOrder() throws Exception{

        Cart cart = CartBuilder.getOrder();
        CartFormDto cartFormDto = CartBuilder.getOrderFormDto();
        CartDto cartDto = CartBuilder.getOrderDto();

        cart.setQuantity(10);
        cartFormDto.setQuantity(10);
        cartDto.setQuantity(10);

        when(cartService.update(anyLong(), any(CartFormDto.class))).thenReturn(cartDto);

        mockMvc.perform(put("/cart/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartFormDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(cartFormDto.getQuantity()))
                .andDo(print());
    }

    @Test
    void deleteOrder() throws Exception{

        Cart cart = CartBuilder.getOrder();

        when(cartService.deleteById(cart.getId())).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(delete("/cart/1")).andExpect(status().isOk()).andDo(print());

    }
}