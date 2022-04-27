package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductOrderFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    Page<OrderDto> findAll(Pageable page);

    OrderDto findById(Long id);

    OrderDto save(OrderFormDto orderFormDto);

    OrderDto update(Long id, OrderFormDto orderFormDto);

    String deleteById(Long id);

}
