package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.*;
import com.pb.ProjetoGrupo2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll
            (@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable page) {
        Page<UserDto> users = this.service.findAll(page);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto user = this.service.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserFormDto userFormDto) {
        UserDto userDto = this.service.save(userFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserFormDto userFormDto) {
        UserDto userDto = this.service.update(id, userFormDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderDto>> listAllOrders(@PathVariable Long id){
        List<OrderDto> orderDto = service.listAllOrders(id);

        if(orderDto.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok().body(orderDto);
        }
    }


    @DeleteMapping("/orders/{orderId}/product/{productId}")
    @Transactional
    public ResponseEntity<?> removeProductOrder(@PathVariable Long productId, @PathVariable Long orderId){

        ResponseEntity<?> product = service.removeProductOrder(productId, orderId);
        return product;
    }
}
