package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.ProductDTO;
import com.pb.ProjetoGrupo2.dto.ProductFormDTO;

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

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> postProduct(@RequestBody ProductFormDTO productFormDTO){
        ProductDTO product = productService.postProduct(productFormDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public Page<ProductDTO> getAllProducts
            (@RequestParam(required = false) String type,
             @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page<ProductDTO> products = productService.getAllProducts(type, pageable);
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> putProduct
            (@PathVariable Long id, @RequestBody @Valid ProductFormDTO productFormDto) {
        ProductDTO product = productService.putProduct(id, productFormDto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String response = productService.deleteById(id);
        return ResponseEntity.ok().body(response);
    }
}
