package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.ProductDTO;
import com.pb.ProjetoGrupo2.dto.ProductFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDTO getProductById(Long id);

    ProductDTO putProduct(Long id, ProductFormDTO productFormDto);

    String deleteById(Long id);

    ProductDTO postProduct(ProductFormDTO productFormDTO);

    Page<ProductDTO> getAllProducts(String type, Pageable pageable);
}
