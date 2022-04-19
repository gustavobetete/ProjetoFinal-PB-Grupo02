package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.builder.OrderBuilder;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import com.pb.ProjetoGrupo2.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void postOrder() throws Exception{

        Order order = OrderBuilder.getOrder();
        OrderDto orderDto = OrderBuilder.getOrderDto();

        when(orderService.save(any())).thenReturn(orderDto);
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order))).andExpect(status().isCreated());
        verify(orderService, times(1)).save(any());

    }

    @Test
    void getOrder() throws Exception{

        OrderDto orderDto = OrderBuilder.getOrderDto();
        OrderDto orderDtoTwo = OrderBuilder.getOrderDtoTwo();

        List<OrderDto> orderDtoList = new ArrayList<>(
                Arrays.asList(orderDto, orderDtoTwo)
        );

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<OrderDto> productDtoPage = new PageImpl<>(orderDtoList, pageRequest, orderDtoList.size());

        when(orderService.findAll(any(PageRequest.class))).thenReturn(productDtoPage);

        mockMvc.perform(get("/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(orderService).findAll(any(PageRequest.class));
        verify(orderService, times(1)).findAll(any(PageRequest.class));
    }


    @Test
    void getOrderById() throws Exception{

        Order order = OrderBuilder.getOrder();
        OrderDto orderDto = OrderBuilder.getOrderDto();

        when(orderService.findById(order.getId())).thenReturn(ResponseEntity.of(Optional.of(orderDto)));

        long id = 1;

        mockMvc.perform(get("/order/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getClass())).andDo(print());

    }

//*****************FAZER TESTE DO PUT*****************

    @Test
    void deleteOrder() throws Exception{

        Order order = OrderBuilder.getOrder();

        when(orderService.deleteById(order.getId())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/order/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk()).andDo(print());
    }
}