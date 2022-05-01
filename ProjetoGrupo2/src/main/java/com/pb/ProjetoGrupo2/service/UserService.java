package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);

    UserDTO postUser(UserFormDTO userFormDto);

    UserDTO putUser(Long id, UpdatedUserFormDTO updatedUserFormDTO);

    String deleteById(Long id);

    OrderedProductDTO postProductIntoOrder(Long userId, Long orderId, OrderedProductFormDTO orderedProductFormDTO);

    Page<UserDTO> getAllUsers(Pageable pageable);

    Page<OrderForUserDTO> getUserOrders(Long userId, Pageable pageable);

    String deleteProductFromUserOrder(Long orderId, Long orderedId);
}
