package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.CartFormDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.service.CartService;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping
    public ResponseEntity<Page<CartDto>> findAll(@PageableDefault(page = 0, size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable page){
        Page<CartDto> cart = this.service.findAll(page);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CartDto> save(@RequestBody @Valid CartFormDto cartFormDto){
        CartDto cartDto = this.service.save(cartFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartDto);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<CartDto> update(@PathVariable Long id, @RequestBody @Valid CartFormDto cartFormDto) {
        CartDto cartDto = this.service.update(id, cartFormDto);
        return ResponseEntity.ok(cartDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/product")
    public ResponseEntity<List<ProductDto>> listAllProduct(@PathVariable Long id){
        List<ProductDto> product = service.listAllProduct(id);

        if(product.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok().body(product);
        }
    }
}
