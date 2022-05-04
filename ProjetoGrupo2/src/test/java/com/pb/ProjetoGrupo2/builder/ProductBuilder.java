package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDTO;
import com.pb.ProjetoGrupo2.dto.ProductFormDTO;
import com.pb.ProjetoGrupo2.dto.UpdateProductStockFormDTO;
import com.pb.ProjetoGrupo2.dto.UpdatedProductFormDTO;
import com.pb.ProjetoGrupo2.entities.Product;

import java.math.BigDecimal;

public class ProductBuilder {

    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Coxinha");
        product.setType(Type.FRITO);
        product.setUnityPrice(new BigDecimal(7.00));
        product.setQuantity(10);
        return product;
    }

    public static ProductDTO getProductDto() {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(1L);
        productDto.setName("Coxinha");
        productDto.setType(Type.FRITO);
        productDto.setUnityPrice(new BigDecimal(7.00));
        productDto.setQuantity(10);
        return productDto;
    }

    public static ProductDTO getProductDtoTwo() {
        ProductDTO productDtoTwo = new ProductDTO();
        productDtoTwo.setId(2L);
        productDtoTwo.setName("Calabresa");
        productDtoTwo.setType(Type.ASSADO);
        productDtoTwo.setUnityPrice(new BigDecimal(5.00));
        productDtoTwo.setQuantity(12);
        return productDtoTwo;
    }

    public static ProductFormDTO getProductFormDto() {
        ProductFormDTO productFormDto = new ProductFormDTO();
        productFormDto.setName("Coxinha");
        productFormDto.setType(Type.FRITO);
        productFormDto.setUnityPrice(new BigDecimal(7.00));
        productFormDto.setQuantity(10);
        return productFormDto;
    }

    public static Product getProductTwo() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Calabresa");
        product.setType(Type.ASSADO);
        product.setUnityPrice(new BigDecimal(5.00));
        product.setQuantity(12);
        return product;
    }

    public static UpdatedProductFormDTO getUpdatedProductFormDTO(){
        UpdatedProductFormDTO updatedProductFormDTO = new UpdatedProductFormDTO();
        updatedProductFormDTO.setName("Fanta");
        updatedProductFormDTO.setType(Type.BEBIDA);
        updatedProductFormDTO.setUnityPrice(new BigDecimal(4));
        return updatedProductFormDTO;
    }

    public static UpdateProductStockFormDTO getUpdateProductStockFormDTO(){
        UpdateProductStockFormDTO updateProductStockFormDTO = new UpdateProductStockFormDTO();
        updateProductStockFormDTO.setQuantity(10);
        return updateProductStockFormDTO;
    }
}
