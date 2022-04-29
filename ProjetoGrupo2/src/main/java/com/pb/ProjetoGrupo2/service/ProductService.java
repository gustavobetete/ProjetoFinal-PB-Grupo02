package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> findAll(Pageable page);

    ProductDto findById(Long id);

    ProductDto save(ProductFormDto ProductFormDto);

    ProductDto update(Long id, ProductFormDto productFormDto);

    String deleteById(Long id);

}
