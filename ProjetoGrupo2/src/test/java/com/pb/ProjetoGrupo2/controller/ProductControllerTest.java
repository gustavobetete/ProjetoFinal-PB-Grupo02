package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

//    @Test
//    void getProducts() throws Exception {
//        List<ProductDto> productDtoList = new ArrayList<ProductDto>(
//                Arrays.asList(new ProductDto(1L,"Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10),
//                        new ProductDto(2L,"Calabresa", Type.ASSADO, BigDecimal.valueOf(5.00), 12),
//                        new ProductDto(3L,"Coca-Cola", Type.BEBIDA, BigDecimal.valueOf(2.50), 20)));
//        when(service.findAll()).thenReturn((ProductDto) productDtoList);
//        mockMvc.perform(get("/api/tutorials"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(productDtoList.size()))
//                .andDo(print());
//    }

    @Test
    void getByIdProduct() throws Exception {
        long id = 1L;
        ProductDto product = new ProductDto(1L,"Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10);
        when(service.findById(id)).thenReturn(product);
        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(product.getName()))
                //.andExpect(jsonPath("$.type").value(product.getType()))
                .andExpect(jsonPath("$.unit_price").value(product.getUnit_price()))
                .andExpect(jsonPath("$.quantity").value(product.getQuantity()))
                .andDo(print());
    }

    @Test
    void  putProduct() throws Exception{
        long id = 1L;
        ProductDto product = new ProductDto(1L,"Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10);
        ProductDto updatedProduct = new ProductDto(1L,"Calabresa", Type.FRITO, BigDecimal.valueOf(7.00), 10);
        when(service.findById(id)).thenReturn(product);
        when(service.save(any(ProductFormDto.class))).thenReturn(updatedProduct);
        mockMvc.perform(put("/product/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedProduct.getName()))
                .andExpect(jsonPath("$.type").value(product.getType()))
                .andExpect(jsonPath("$.unit_price").value(updatedProduct.getUnit_price()))
                .andExpect(jsonPath("$.quantity").value(updatedProduct.getQuantity()))
                .andDo(print());
    }

    @Test
    void shouldDeleteTutorial() throws Exception {
        long id = 1L;
        when(service.deleteById(id)).thenReturn(ResponseEntity.notFound().build());
        mockMvc.perform(delete("/product/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}