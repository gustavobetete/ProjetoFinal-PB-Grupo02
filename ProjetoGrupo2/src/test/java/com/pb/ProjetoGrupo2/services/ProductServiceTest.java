package com.pb.ProjetoGrupo2.services;

import com.pb.ProjetoGrupo2.builder.ProductBuilder;
import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Product Service")
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository repository;

    @Test
    @DisplayName("Save Product")
    public void saveProduct() {
        Product product = ProductBuilder.getProduct();

        when(this.repository.save(any(Product.class))).thenReturn(product);

        ProductDto productDTO = this.productService.save(ProductBuilder.getProductFormDto());

        assertThat(productDTO.getId()).isNotNull();
        assertThat(productDTO.getName()).isEqualTo(product.getName());
        assertThat(productDTO.getType()).isEqualTo(product.getType());
        assertThat(productDTO.getQuantityInStock()).isEqualTo(product.getQuantityInStock());
        assertThat(productDTO.getUnitPrice()).isEqualTo(product.getUnitPrice());
    }

    @Test
    @DisplayName("List all product")
    public void listProducts() {
        Product product = ProductBuilder.getProduct();

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Product> products = Arrays.asList(product);
        Page<Product> page = new PageImpl<>(products, pageRequest, 1);

        when(this.repository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<ProductDto> pageProductDTO = this.productService.findAll(pageRequest);

        assertThat(pageProductDTO.getContent()).hasSize(1);
        assertThat(pageProductDTO.getTotalPages()).isEqualTo(1);
        assertThat(pageProductDTO.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("FindById products")
    public void findByIdProduct() {
        Product product = ProductBuilder.getProduct();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductDto productDTO = this.productService.findById(product.getId());

        assertThat(productDTO.getId()).isNotNull();
        assertThat(productDTO.getName()).isEqualTo(product.getName());
        assertThat(productDTO.getType()).isEqualTo(product.getType());
        assertThat(productDTO.getQuantityInStock()).isEqualTo(product.getQuantityInStock());
        assertThat(productDTO.getUnitPrice()).isEqualTo(product.getUnitPrice());
    }

    @Test
    @DisplayName("findById product not found")
    public void findByIdProduct_NotFound() {
        Product product = ProductBuilder.getProduct();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.productService.findById(product.getId()));
    }


    @Test
    @DisplayName("Update product")
    public void updateProduct() {
        Product product = ProductBuilder.getProduct();
        ProductFormDto productFormDTO = ProductBuilder.getProductFormDto();
        productFormDTO.setName("Product Test");

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(product));
        when(this.repository.save(any(Product.class))).thenReturn(product);

        ProductDto productDTO = this.productService.update(product.getId(), productFormDTO);

        assertThat(productDTO.getId()).isNotNull();
        assertThat(productDTO.getName()).isEqualTo(productFormDTO.getName());
        assertThat(productDTO.getType()).isEqualTo(productFormDTO.getType());
        assertThat(productDTO.getQuantityInStock()).isEqualTo(productFormDTO.getQuantityInStock());
        assertThat(productDTO.getUnitPrice()).isEqualTo(productFormDTO.getUnitPrice());
    }

    @Test
    @DisplayName("Update product not found")
    public void updateProduct_NotFound() {
        Product product = ProductBuilder.getProduct();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.productService.update(product.getId(), ProductBuilder.getProductFormDto()));
    }

    @Test
    @DisplayName("Delete product")
    public void deleteProduct() {
        Product product = ProductBuilder.getProduct();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(product));

        this.productService.deleteById(1L);

        verify(this.repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete Product not found")
    public void deleteProduct_NotFound() {
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.productService.deleteById(1L));
    }
}

