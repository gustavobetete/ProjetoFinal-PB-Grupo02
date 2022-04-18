package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.service.OrderService;
import com.pb.ProjetoGrupo2.service.PromotionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService service;

    @Test
    void postOrder() throws Exception {
        OrderDto Order = new OrderDto(1L, 1,
                Timestamp.valueOf("2022-05-23 12:41:00"), Timestamp.valueOf("2022-05-23 22:00:00"));
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Order)))
                .andExpect(status().isCreated())
                .andDo(print());;
    }

    @Test
    void deleteOrder() throws Exception {
        long id = 1L;
        when(service.deleteById(id)).thenReturn(ResponseEntity.notFound().build());
        mockMvc.perform(delete("/promotion/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}