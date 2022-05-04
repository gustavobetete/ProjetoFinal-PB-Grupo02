package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.*;
import com.pb.ProjetoGrupo2.entities.OrderedProduct;
import com.pb.ProjetoGrupo2.entities.Product;

import java.math.BigDecimal;

public class ProductBuilder {

    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Coxinha");
        product.setType(Type.FRITO);
        product.setUnityPrice(new BigDecimal(5));
        product.setQuantity(10);
        return product;
    }

    public static ProductDTO getProductDto() {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(1L);
        productDto.setName("Coxinha");
        productDto.setType(Type.FRITO);
        productDto.setUnityPrice(new BigDecimal(5));
        productDto.setQuantity(10);
        return productDto;
    }

    public static ProductDTO getProductDtoTwo() {
        ProductDTO productDtoTwo = new ProductDTO();
        productDtoTwo.setId(2L);
        productDtoTwo.setName("Calabresa");
        productDtoTwo.setType(Type.ASSADO);
        productDtoTwo.setUnityPrice(new BigDecimal(5));
        productDtoTwo.setQuantity(12);
        return productDtoTwo;
    }

    public static ProductFormDTO getProductFormDto() {
        ProductFormDTO productFormDto = new ProductFormDTO();
        productFormDto.setName("Coxinha");
        productFormDto.setType(Type.FRITO);
        productFormDto.setUnityPrice(new BigDecimal(5));
        productFormDto.setQuantity(10);
        return productFormDto;
    }

    public static Product getProductTwo() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Calabresa");
        product.setType(Type.ASSADO);
        product.setUnityPrice(new BigDecimal(5));
        product.setQuantity(12);
        return product;
    }


    public static UpdatedProductFormDTO getUpdatedProductFormDTO() {
        UpdatedProductFormDTO updatedProductFormDTO = new UpdatedProductFormDTO();
        updatedProductFormDTO.setName("Calabresa");
        updatedProductFormDTO.setType(Type.ASSADO);
        updatedProductFormDTO.setUnityPrice(new BigDecimal(5));
        return updatedProductFormDTO;
    }

    public static UpdatedProductFormDTO getUpdatedProductFormDTOTwo() {
        UpdatedProductFormDTO updatedProductFormDTO = new UpdatedProductFormDTO();
        updatedProductFormDTO.setName("Fanta");
        updatedProductFormDTO.setType(Type.BEBIDA);
        updatedProductFormDTO.setUnityPrice(new BigDecimal(4));
        return updatedProductFormDTO;
    }


    public static UpdateProductStockFormDTO getUpdateProductStockFormDTO() {
        UpdateProductStockFormDTO updateProductStockFormDTO = new UpdateProductStockFormDTO();
        updateProductStockFormDTO.setQuantity(12);
        return updateProductStockFormDTO;
    }

    public static OrderedProduct getOrderedProduct(){
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setId(1L);
        orderedProduct.setName("Coxinha");
        orderedProduct.setType(Type.FRITO);
        orderedProduct.setOrderedQuantity(10);
        orderedProduct.setUnityPrice(new BigDecimal(5));
        return orderedProduct;
    }

    public static OrderedProduct getOrderedProductTwo(){
        OrderedProduct orderedProductTwo = new OrderedProduct();
        orderedProductTwo.setId(2L);
        orderedProductTwo.setName("Calabresa");
        orderedProductTwo.setType(Type.ASSADO);
        orderedProductTwo.setOrderedQuantity(10);
        orderedProductTwo.setUnityPrice(new BigDecimal(5));
        return orderedProductTwo;
    }

    public static OrderedProductFormDTO getOrderedProductFormDTO(){
        OrderedProductFormDTO orderedProductFormDTO = new OrderedProductFormDTO();
        orderedProductFormDTO.setProductId(1L);
        orderedProductFormDTO.setOrderedQuantity(10);
        return orderedProductFormDTO;
    }

    public static OrderedProductFormDTO getOrderedProductFormDTOTwo(){
        OrderedProductFormDTO orderedProductFormDTOTwo = new OrderedProductFormDTO();
        orderedProductFormDTOTwo.setProductId(2L);
        orderedProductFormDTOTwo.setOrderedQuantity(10);
        return orderedProductFormDTOTwo;
    }

    public static OrderedProductDTO getOrderedProductDTO(){
        OrderedProductDTO orderedProductDTO = new OrderedProductDTO();
        orderedProductDTO.setId(1L);
        orderedProductDTO.setName("Coxinha");
        orderedProductDTO.setUnityPrice(new BigDecimal(5));
        return orderedProductDTO;
    }

    public static OrderedProductDTO getOrderedProductDTOTwo(){
        OrderedProductDTO orderedProductDTOTwo = new OrderedProductDTO();
        orderedProductDTOTwo.setId(2L);
        orderedProductDTOTwo.setName("Calabresa");
        orderedProductDTOTwo.setUnityPrice(new BigDecimal(5));
        return orderedProductDTOTwo;
    }
}
