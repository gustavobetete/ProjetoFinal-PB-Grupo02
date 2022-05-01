package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.OrderDTO;
import com.pb.ProjetoGrupo2.dto.OrderFormDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductDTO;
import com.pb.ProjetoGrupo2.dto.StatusUpdateFormDTO;
import com.pb.ProjetoGrupo2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderFormDTO orderFormDTO){
        OrderDTO order = orderService.postOrder(orderFormDTO);
        if (order != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<OrderDTO> getAllOrders(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<OrderDTO> orders = orderService.getAllOrders(pageable);
        return orders;
    }

    @GetMapping("/{orderId}/product")
    public Page<OrderedProductDTO> getOrderProduct
            (@PathVariable Long orderId,
             @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        Page<OrderedProductDTO> orderedProducts = orderService.getOrderProduct(orderId, pageable);
        return orderedProducts;
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> putOrderStatus
            (@PathVariable Long orderId, @RequestBody StatusUpdateFormDTO statusUpdateFormDTO){

        OrderDTO order = orderService.putOrderStatus(orderId ,statusUpdateFormDTO);
        if (order != null){
            return ResponseEntity.ok().body(order);
        }
        return ResponseEntity.noContent().build();
    }
}
