package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Mock
    private Order.OrderBuilder order;
    private Order.OrderBuilder orderTwo;
    private OrderDto orderDto;
    private OrderDto orderDtoTwo;
    private OrderFormDto orderFormDto;
    private OrderFormDto orderFormDtoTwo;

    @BeforeEach
    public void beforeEach(){

        order = Order.builder()
                .id(1L)
                .quantity(1)
                .purchase_date(Timestamp.valueOf("2022-05-23 12:41:00"))
                .delivery_date(Timestamp.valueOf("2022-05-23 22:00:00"));

        orderTwo =  Order.builder()
                .id(2L)
                .quantity(2)
                .purchase_date(Timestamp.valueOf("2022-05-01 16:23:00"))
                .delivery_date(Timestamp.valueOf("2022-05-01 22:00:00"));

        orderDto = modelMapper.map(order, OrderDto.class);
        orderFormDto = modelMapper.map(order, OrderFormDto.class);

        orderDtoTwo = modelMapper.map(orderDtoTwo, OrderDto.class);
        orderFormDtoTwo = modelMapper.map(orderFormDtoTwo, OrderFormDto.class);

    }

    @AfterEach
    public void afterEach(){

        order = null;
        orderTwo = null;

        orderDto = null;
        orderFormDto = null;

        orderDtoTwo = null;
        orderFormDtoTwo = null;

    }

    @Test
    void postOrder() throws Exception{

        when(orderService.save(any())).thenReturn(orderDto);
        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order))).andExpect(status().isCreated());
        verify(orderService, times(1)).save(any());

    }

    @Test
    void getOrder() throws Exception{

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

        OrderDto.OrderDtoBuilder orderDtoBuilder = OrderDto.builder()
                .id(1L)
                .quantity(1)
                .purchase_date(Timestamp.valueOf("2022-05-23 12:41:00"))
                .delivery_date(Timestamp.valueOf("2022-05-23 22:00:00"));

        when(orderService.findById(order.build().getId())).thenReturn(ResponseEntity.of(Optional.of(orderDto)));

        long id = 1;

        mockMvc.perform(get("/order/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(order.getClass())).andDo(print());

    }

//*****************FAZER TESTE DO PUT*****************

    @Test
    void deleteOrder() throws Exception{

        when(orderService.deleteById(order.build().getId())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/order/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk()).andDo(print());
    }
}