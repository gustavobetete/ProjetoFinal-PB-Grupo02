package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.OrderStatus;
import com.pb.ProjetoGrupo2.dto.DailyReportDTO;
import com.pb.ProjetoGrupo2.dto.OrderDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductFormDTO;
import com.pb.ProjetoGrupo2.entities.DailyReport;
import com.pb.ProjetoGrupo2.entities.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderBuilder {

    public static Order getOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setPurchaseDate(LocalDate.now());
        order.setTotalPrice(new BigDecimal(15));
        order.setStatus(OrderStatus.OPEN);
        return order;
    }

    public static Order getOrderTwo() {
        Order orderTwo = new Order();
        orderTwo.setId(2L);
        orderTwo.setPurchaseDate(LocalDate.now());
        orderTwo.setTotalPrice(new BigDecimal(17));
        orderTwo.setStatus(OrderStatus.OPEN);
        return orderTwo;
    }

    public static OrderDTO getOrderDto() {
        OrderDTO orderDto = new OrderDTO();
        orderDto.setId(1L);
        orderDto.setPurchaseDate(LocalDate.now());
        orderDto.setTotalPrice(new BigDecimal(15));
        orderDto.setStatus(OrderStatus.OPEN);
        return orderDto;
    }

    public static OrderDTO getOrderDtoTwo() {
        OrderDTO orderDtoTwo = new OrderDTO();
        orderDtoTwo.setId(2L);
        orderDtoTwo.setPurchaseDate(LocalDate.now());
        orderDtoTwo.setTotalPrice(new BigDecimal(17));
        orderDtoTwo.setStatus(OrderStatus.OPEN);
        return orderDtoTwo;
    }

    public static OrderedProductFormDTO getOrderedProductFormDTO(){
        OrderedProductFormDTO orderedProductFormDTO = new OrderedProductFormDTO();
        orderedProductFormDTO.setProductId(1L);
        orderedProductFormDTO.setOrderedQuantity(10);
        return orderedProductFormDTO;
    }

    public static OrderedProductDTO getOrderedProductDTO(){
        OrderedProductDTO orderedProductDTO = new OrderedProductDTO();
        orderedProductDTO.setId(1L);
        orderedProductDTO.setName("Coxinha");
        orderedProductDTO.setUnityPrice(new BigDecimal(7));
        return orderedProductDTO;
    }

    public static DailyReport getDailyReport(){
        DailyReport dailyReport = new DailyReport();
        dailyReport.setId(1L);
        dailyReport.setGenerateDate(LocalDate.now());
        dailyReport.setTotalAmountSold(new BigDecimal(100));
        return dailyReport;
    }

    public static DailyReportDTO getDailyReportDTO(){
        DailyReportDTO dailyReportDTO = new DailyReportDTO();
        dailyReportDTO.setId(1L);
        dailyReportDTO.setGenerateDate(LocalDate.now());
        dailyReportDTO.setTotalAmountSold(new BigDecimal(100));
        return dailyReportDTO;
    }
//
//    public static OrderDto getOrderDtoTwo() {
//        OrderDto orderDtoTwo = new OrderDto();
//        orderDtoTwo.setId(2L);
//        orderDtoTwo.setPurchaseDate(LocalDateTime.parse("2022-04-26T12:00:00"));
//        orderDtoTwo.setDeliveryDate(LocalDateTime.parse("2022-04-26T22:00:00"));
//        orderDtoTwo.setIdUser(UserBuilder.getUser().getId());
//        orderDtoTwo.setProducts(ProductBuilder.getProducts());
//        return orderDtoTwo;
//    }

//    public static OrderFormDto getOrderFormDto() {
//        OrderFormDto orderFormDto = new OrderFormDto();
//        orderFormDto.setIdUser(UserBuilder.getUser().getId());
//        orderFormDto.setProducts(Arrays.asList(ProductBuilder.getProductOrderFormDto()));
//
//        return orderFormDto;
//    }
}

