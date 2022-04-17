package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    Page<OrderDto> findAll(Pageable page);

    ResponseEntity<OrderDto> findById(Long id);

    OrderDto save(OrderFormDto orderFormDto);

    OrderDto update(Long id, OrderFormDto orderFormDto);

    ResponseEntity<Object> deleteById(Long id);
}
