package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.builder.ProductBuilder;
import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.service.ProductService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void postProduct() throws Exception{

        Product product = ProductBuilder.getProduct();
        ProductDto productDto = ProductBuilder.getProductDto();

        when(productService.save(any())).thenReturn(productDto);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andDo(print());
    }

    @Test
    void getProducts() throws Exception{

        List<ProductDto> productDtoList = new ArrayList<>(
                Arrays.asList(ProductBuilder.getProductDto(), ProductBuilder.getProductDtoTwo())
        );

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList, pageRequest, productDtoList.size());

        when(productService.findAll(any(PageRequest.class))).thenReturn(productDtoPage);

        mockMvc.perform(get("/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].name").value("Coxinha"))
                .andExpect(jsonPath("$.content.[1].name").value("Calabresa"))
                .andDo(print());

    }

    @Test
    void getProductById() throws Exception{

        Product product = ProductBuilder.getProduct();
        ProductDto productDto = ProductBuilder.getProductDto();

        when(productService.findById(product.getId())).thenReturn(productDto);

        long id = 1;

        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value(product.getName())).andDo(print());

    }

    @Test
    void updateProduct() throws Exception{

        Product product = ProductBuilder.getProduct();
        ProductFormDto productFormDto = ProductBuilder.getProductFormDto();
        ProductDto productDto = ProductBuilder.getProductDto();

        product.setName("Calabresa");
        productFormDto.setName("Calabresa");
        productDto.setName("Calabresa");

        when(productService.update(anyLong(), any(ProductFormDto.class))).thenReturn(productDto);

        mockMvc.perform(put("/product/1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productFormDto)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name")
                        .value(productFormDto.getName())).andDo(print());

    }

    @Test
    void deleteProduct() throws Exception{

        Product product = ProductBuilder.getProduct();
        ProductDto productDto = ProductBuilder.getProductDto();

        when(productService.deleteById(product.getId())).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(delete("/product/1")).andExpect(status().isOk()).andDo(print());

    }
}
