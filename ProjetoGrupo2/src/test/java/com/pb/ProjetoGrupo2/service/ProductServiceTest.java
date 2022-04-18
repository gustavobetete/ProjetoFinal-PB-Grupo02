package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    ProductService productService;

    @BeforeEach
    void initUseCase() {
        productService = new ProductServiceImpl(productRepository);
    }

//    @Test
//    public void savedProduct_Success() {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Coxinha");
//        product.setType(Type.FRITO);
//        product.setUnitPrice(BigDecimal.valueOf(7.00));
//        product.setQuantity(10);
//
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        Product savedProduct = productRepository.save(product);
//        assertThat(savedProduct.getName()).isNotNull();
//    }
//
//    @Test
//    public void product_exists_in_db_success() {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Coxinha");
//        product.setType(Type.FRITO);
//        product.setUnitPrice(BigDecimal.valueOf(7.00));
//        product.setQuantity(10);
//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//
//        when(productRepository.findAll()).thenReturn(productList);
//
//        Page<ProductDto> fetchedProduct = productService.findAll(5);
//        assertThat(fetchedProduct.size()).isGreaterThan(0);
//    }
}