package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<OrderDto> findAll(Pageable page);

    OrderDto findById(Long id);

    OrderDto save(OrderFormDto orderFormDto);

    OrderDto update(Long id, OrderFormDto orderFormDto);

    Object deleteById(Long id);
}
