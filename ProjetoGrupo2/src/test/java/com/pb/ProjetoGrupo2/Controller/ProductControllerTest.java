package com.pb.ProjetoGrupo2.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.service.ProductService;
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

import java.math.BigDecimal;
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

    @Mock
    private Product product;
    private Product productTwo;
    private ProductDto productDto;
    private ProductDto productDtoTwo;
    private ProductFormDto productFormDto;
    private ProductFormDto productFormDtoTwo;

    @BeforeEach
    public void beforeEach(){

        product = Product.builder()
                .id(1L)
                .name("Coxinha")
                .type(Type.FRITO)
                .unit_price(BigDecimal.valueOf(7.00))
                .quantity(10)
                .build();

        productTwo = Product.builder()
                .id(2L)
                .name("Calabresa")
                .type(Type.FRITO)
                .unit_price(BigDecimal.valueOf(7.00))
                .quantity(10)
                .build();

        productDto = modelMapper.map(product, ProductDto.class);
        productFormDto = modelMapper.map(product, ProductFormDto.class);

        productDtoTwo = modelMapper.map(productTwo, ProductDto.class);
        productFormDtoTwo = modelMapper.map(productTwo, ProductFormDto.class);

    }

    @AfterEach
    public void afterEach(){

        product = null;
        productTwo = null;

        productDto = null;
        productFormDto = null;

        productDtoTwo = null;
        productFormDtoTwo = null;

    }

    @Test
    void postProduct() throws Exception{

        when(productService.save(any())).thenReturn(productDto);
        mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))).andExpect(status().isCreated());

    }

    @Test
    void getProducts() throws Exception{

        List<ProductDto> productDtoList = new ArrayList<>(
                Arrays.asList(productDto, productDtoTwo)
        );

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<ProductDto> productDtoPage = new PageImpl<>(productDtoList, pageRequest, productDtoList.size());

        when(productService.findAll(any(PageRequest.class))).thenReturn(productDtoPage);

        mockMvc.perform(get("/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    void getProductById() throws Exception{

        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Coxinha")
                .type(Type.FRITO)
                .unit_price(BigDecimal.valueOf(7.00))
                .quantity(10)
                .build();

        when(productService.findById(product.getId())).thenReturn(ResponseEntity.ok(productDto));

        long id = 1;

        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName())).andDo(print());

    }

//*****************FAZER TESTE DO PUT*****************

    @Test
    void deleteProduct() throws Exception{

        when(productService.deleteById(product.getId())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/product/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk()).andDo(print());
    }
}
