package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void initUseCase() {
        List<Product> productList = Arrays.asList(
                new Product( 1L, "Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10)
        );
        productRepository.saveAll(productList);
    }

    @AfterEach
    public void destroyAll(){
        productRepository.deleteAll();
    }

    @Test
    void saveAll_success() {
        List<Product> productList = Arrays.asList(
                new Product(1L, "Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10),
                new Product(2L, "Calabresa", Type.ASSADO, BigDecimal.valueOf(5.00), 12),
                new Product(3L, "Coca-Cola", Type.BEBIDA, BigDecimal.valueOf(2.50), 30)
        );
        Iterable<Product> allProduct = productRepository.saveAll(productList);

        AtomicInteger validIdFound = new AtomicInteger();
        allProduct.forEach(customer -> {
            if(customer.getId()>0){
                validIdFound.getAndIncrement();
            }
        });

        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void findAll_success() {
        List<Product> allProduct = productRepository.findAll();
        assertThat(allProduct.size()).isGreaterThanOrEqualTo(1);
    }
}