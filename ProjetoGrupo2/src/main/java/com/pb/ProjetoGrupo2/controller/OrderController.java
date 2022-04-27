package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
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

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> findAll(@PageableDefault(page = 0, size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable page){
        Page<OrderDto> orders = this.service.findAll(page);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderFormDto orderFormDto){
        OrderDto orderDto = this.service.save(orderFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String response = this.service.deleteById(id);
        return ResponseEntity.ok().body(response);
    }

}
