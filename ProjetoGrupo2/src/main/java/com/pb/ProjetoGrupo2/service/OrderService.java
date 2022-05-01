package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDTO postOrder(OrderFormDTO orderFormDTO);

    Page<OrderDTO> getAllOrders(Pageable pageable);

    Page<OrderedProductDTO> getOrderProduct(Long orderId, Pageable pageable);

    OrderDTO putOrderStatus(Long orderId, StatusUpdateFormDTO statusUpdateFormDTO);
}
