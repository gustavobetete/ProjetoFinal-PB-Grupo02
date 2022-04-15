package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService service;

    @Test
    void postProduct() throws Exception {
        ProductDto product = new ProductDto("Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10);
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andDo(print());;
    }

    void getProduct() throws Exception {
        long id = 1L;
        ProductDto product = new ProductDto("Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10);
        when(service.findById(id)).thenReturn(product);
        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.type").value(product.getType()))
                .andExpect(jsonPath("$.unit_price").value(product.getUnit_price()))
                .andExpect(jsonPath("$.quantity").value(product.getQuantity()))
                .andDo(print());
    }

}