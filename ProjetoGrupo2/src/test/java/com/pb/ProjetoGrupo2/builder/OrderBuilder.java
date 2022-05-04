package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.constants.OrderStatus;
import com.pb.ProjetoGrupo2.dto.DailyReportDTO;
import com.pb.ProjetoGrupo2.dto.OrderDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductFormDTO;
import com.pb.ProjetoGrupo2.entities.DailyReport;
import com.pb.ProjetoGrupo2.entities.Order;


import com.pb.ProjetoGrupo2.dto.*;
import com.pb.ProjetoGrupo2.entities.OrderedProduct;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderBuilder {

    public static OrderDTO getOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setPurchaseDate(LocalDate.now());
        orderDTO.setTotalPrice(new BigDecimal(7));
        orderDTO.setStatus(OrderStatus.OPEN);
        return orderDTO;
    }

    public static OrderDTO getOrderDTOTwo() {
        OrderDTO orderDTOTwo = new OrderDTO();
        orderDTOTwo.setId(2L);
        orderDTOTwo.setPurchaseDate(LocalDate.now());
        orderDTOTwo.setTotalPrice(new BigDecimal(7));
        orderDTOTwo.setStatus(OrderStatus.OPEN);
        return orderDTOTwo;
    }

    public static OrderFormDTO getOrderFormDTO() {
        OrderFormDTO orderFormDTO = new OrderFormDTO();
        orderFormDTO.setUserId(2L);
        return orderFormDTO;
    }

    public static OrderStatusUpdateFormDTO getOrderStatusUpdateFormDTO() {
        OrderStatusUpdateFormDTO orderStatusUpdateFormDTO = new OrderStatusUpdateFormDTO();
        orderStatusUpdateFormDTO.setStatus(OrderStatus.OPEN);
        return orderStatusUpdateFormDTO;
    }

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

    public static DailyReport getDailyReportTwo(){
        DailyReport dailyReportTwo = new DailyReport();
        dailyReportTwo.setId(2L);
        dailyReportTwo.setGenerateDate(LocalDate.now());
        dailyReportTwo.setTotalAmountSold(new BigDecimal(150));
        return dailyReportTwo;
    }

    public static DailyReportDTO getDailyReportDTOTwo(){
        DailyReportDTO dailyReportDTOTwo = new DailyReportDTO();
        dailyReportDTOTwo.setId(2L);
        dailyReportDTOTwo.setGenerateDate(LocalDate.now());
        dailyReportDTOTwo.setTotalAmountSold(new BigDecimal(150));
        return dailyReportDTOTwo;
    }

    public static OrderForUserDTO getOrderForUserDTO(){
        OrderForUserDTO orderForUserDTO = new OrderForUserDTO();
        orderForUserDTO.setId(1L);
        orderForUserDTO.setPurchaseDate(LocalDate.now());
        orderForUserDTO.setTotalPrice(new BigDecimal(15));
        orderForUserDTO.setStatus(OrderStatus.OPEN);
        return orderForUserDTO;
    }

    public static OrderForUserDTO getOrderForUserDTOTwo(){
        OrderForUserDTO orderForUserDTOTwo = new OrderForUserDTO();
        orderForUserDTOTwo.setId(2L);
        orderForUserDTOTwo.setPurchaseDate(LocalDate.now());
        orderForUserDTOTwo.setTotalPrice(new BigDecimal(17));
        orderForUserDTOTwo.setStatus(OrderStatus.OPEN);
        return orderForUserDTOTwo;
    }

}

