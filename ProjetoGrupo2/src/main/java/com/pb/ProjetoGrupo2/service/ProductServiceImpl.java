package com.pb.ProjetoGrupo2.service;



import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;


    public Page<ProductDto> findAll(Pageable page){
        Page<Product> products = this.repository.findAll(page);
        List<ProductDto> listProducts = products.getContent().stream().map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return new PageImpl<ProductDto>(listProducts, page, products.getTotalElements());
    }

    public ProductDto save(ProductFormDto productFormDto){
        Product product = this.repository.save(mapper.map(productFormDto, Product.class));
        return mapper.map(product, ProductDto.class);
    }

    public ProductDto search(Long id) {
        Optional<Product> product = this.repository.findById(id);
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(Long id, ProductFormDto productFormDto) {
        Optional<Product> product = this.repository.findById(id);
        if(product.isPresent()) {
            Product productUpdated = mapper.map(productFormDto, Product.class);
            productUpdated.setId(id);
            repository.save(productUpdated);
            return mapper.map(productUpdated, ProductDto.class);
        }
        throw new ObjectNotFoundException("Product not found!");
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
