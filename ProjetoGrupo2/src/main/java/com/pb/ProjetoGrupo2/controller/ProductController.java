package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.dto.ProductOrderFormDto;
import com.pb.ProjetoGrupo2.service.ProductService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(@PageableDefault(page = 0, size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable page){
        Page<ProductDto> products = this.service.findAll(page);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody @Valid ProductFormDto productFormDto){
        ProductDto productDto = this.service.save(productFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductFormDto productFormDto) {
        ProductDto productDto = this.service.update(id, productFormDto);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/orders")
    @Transactional
    public ResponseEntity<?> createProductOrder(@RequestBody @Valid ProductOrderFormDto productOrderFormDto){
        ResponseEntity productOrder = service.createProductOrder(productOrderFormDto);
        return productOrder;
    }
}
