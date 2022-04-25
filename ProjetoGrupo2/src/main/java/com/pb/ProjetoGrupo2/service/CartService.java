package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.CartFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {

    Page<CartDto> findAll(Pageable page);

    CartDto findById(Long id);

    CartDto save(CartFormDto cartFormDto);

    CartDto update(Long id, CartFormDto cartFormDto);

    Object deleteById(Long id);

    List listAllProduct(Long id);
}
