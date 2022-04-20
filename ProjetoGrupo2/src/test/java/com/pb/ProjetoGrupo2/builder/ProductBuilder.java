package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.entities.Product;

import java.math.BigDecimal;

public class ProductBuilder {
    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Coxinha");
        product.setType(Type.FRITO);
        product.setUnit_price(BigDecimal.valueOf(7.00));
        product.setQuantity(10);
        return product;
    }

    public static ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Coxinha");
        productDto.setType(Type.FRITO);
        productDto.setUnit_price(BigDecimal.valueOf(7.00));
        productDto.setQuantity(10);
        return productDto;
    }

    public static ProductDto getProductDtoTwo() {
        ProductDto productDtoTwo = new ProductDto();
        productDtoTwo.setId(2L);
        productDtoTwo.setName("Calabresa");
        productDtoTwo.setType(Type.ASSADO);
        productDtoTwo.setUnit_price(BigDecimal.valueOf(5.00));
        productDtoTwo.setQuantity(12);
        return productDtoTwo;
    }

    public static ProductFormDto getProductFormDto() {
        ProductFormDto productFormDto = new ProductFormDto();
        productFormDto.setName("Coxinha");
        productFormDto.setType(Type.FRITO);
        productFormDto.setUnit_price(BigDecimal.valueOf(7.00));
        productFormDto.setQuantity(10);
        return productFormDto;
    }
    public static Product getProductTwo() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Calabresa");
        product.setType(Type.ASSADO);
        product.setUnit_price(BigDecimal.valueOf(5.00));
        product.setQuantity(12);
        return product;
    }

}
