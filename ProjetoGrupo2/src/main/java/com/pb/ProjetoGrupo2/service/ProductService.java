package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> findAll(Pageable page);

    ProductDto save(ProductFormDto ProductFormDto);

    ProductDto search(Long id);

    ProductDto update(Long id, ProductFormDto productFormDto);

    void delete(Long id);
}
