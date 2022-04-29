package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<OrderDto> findAll(Pageable page);

    OrderDto findById(Long id);

    OrderDto save(OrderFormDto orderFormDto);

    OrderDto update(Long id, OrderFormDto orderFormDto);

    String deleteById(Long id);

}
