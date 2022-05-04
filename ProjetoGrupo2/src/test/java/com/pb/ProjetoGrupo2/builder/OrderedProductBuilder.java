package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.OrderedProductDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductFormDTO;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.entities.OrderedProduct;
import com.pb.ProjetoGrupo2.entities.Product;

import java.math.BigDecimal;

public class OrderedProductBuilder {

    public static OrderedProduct getOrderedProduct(){

        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setId(1L);
        orderedProduct.setName(ProductBuilder.getProduct().getName());
        orderedProduct.setType(ProductBuilder.getProduct().getType());
        orderedProduct.setOrderedQuantity(1);
        orderedProduct.setUnityPrice(new BigDecimal(7));
        orderedProduct.setOrder(OrderBuilder.getOrder());
        orderedProduct.setProduct(ProductBuilder.getProduct());
        return orderedProduct;
    }

    public static OrderedProductDTO getOrderedProductDTO(){

        OrderedProductDTO orderedProductDTO = new OrderedProductDTO();
        orderedProductDTO.setId(1L);
        orderedProductDTO.setName(ProductBuilder.getProduct().getName());
        orderedProductDTO.setUnityPrice(new BigDecimal(7));
        return orderedProductDTO;
    }

    public static OrderedProductFormDTO getOrderedProductFormDTO(){

        OrderedProductFormDTO orderedProductFormDTO = new OrderedProductFormDTO();
        orderedProductFormDTO.setOrderedQuantity(1);
        orderedProductFormDTO.setProductId(ProductBuilder.getProduct().getId());
        return orderedProductFormDTO;
    }


}
