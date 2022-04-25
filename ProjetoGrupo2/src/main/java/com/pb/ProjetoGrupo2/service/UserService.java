package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.ProductCartFormDto;
import com.pb.ProjetoGrupo2.dto.UserDto;
import com.pb.ProjetoGrupo2.dto.UserFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    Page<UserDto> findAll(Pageable page);

    UserDto findById(Long id);

    UserDto save(UserFormDto userFormDto);

    UserDto update(Long id, UserFormDto userFormDto);

    Object deleteById(Long id);

    List<CartDto> listAllOrders(Long id);

    ResponseEntity createProductOrder(ProductCartFormDto productCartFormDto);

    ResponseEntity removeProductOrder(Long productId, Long orderId);
}
