//package com.pb.ProjetoGrupo2.builder;
//
//import com.pb.ProjetoGrupo2.constants.Type;
//import com.pb.ProjetoGrupo2.dto.ProductDto;
//import com.pb.ProjetoGrupo2.dto.ProductFormDto;
//import com.pb.ProjetoGrupo2.dto.ProductOrderFormDto;
//import com.pb.ProjetoGrupo2.entities.Product;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductBuilder {
//
//    public static Product getProduct() {
//        Product product = new Product();
//        product.setId(1L);
//        product.setName("Coxinha");
//        product.setType(Type.FRITO);
//        product.setUnitPrice(Double.valueOf(7.00));
//        product.setQuantity(10);
//        return product;
//    }
//
//    public static List<Product> getProducts(){
//        List<Product> products = new ArrayList<>();
//
//        products.add(getProduct());
//
//        return products;
//    }
//
//    public static ProductDto getProductDto() {
//        ProductDto productDto = new ProductDto();
//        productDto.setId(1L);
//        productDto.setName("Coxinha");
//        productDto.setType(Type.FRITO);
//        productDto.setUnitPrice(Double.valueOf(7.00));
//        productDto.setQuantity(10);
//        return productDto;
//    }
//
//    public static ProductDto getProductDtoTwo() {
//        ProductDto productDtoTwo = new ProductDto();
//        productDtoTwo.setId(2L);
//        productDtoTwo.setName("Calabresa");
//        productDtoTwo.setType(Type.ASSADO);
//        productDtoTwo.setUnitPrice(Double.valueOf(5.00));
//        productDtoTwo.setQuantity(12);
//        return productDtoTwo;
//    }
//
//    public static ProductFormDto getProductFormDto() {
//        ProductFormDto productFormDto = new ProductFormDto();
//        productFormDto.setName("Coxinha");
//        productFormDto.setType(Type.FRITO);
//        productFormDto.setUnitPrice(Double.valueOf(7.00));
//        productFormDto.setQuantity(10);
//        return productFormDto;
//    }
//    public static Product getProductTwo() {
//        Product product = new Product();
//        product.setId(2L);
//        product.setName("Calabresa");
//        product.setType(Type.ASSADO);
//        product.setUnitPrice(Double.valueOf(5.00));
//        product.setQuantity(12);
//        return product;
//    }
//
//    public static ProductOrderFormDto getProductOrderFormDto() {
//        ProductOrderFormDto productOrderFormDto = new ProductOrderFormDto();
//        productOrderFormDto.setProductId(getProduct().getId());
//        productOrderFormDto.setQuantity(getProduct().getQuantity());
//        return productOrderFormDto;
//    }
//}
