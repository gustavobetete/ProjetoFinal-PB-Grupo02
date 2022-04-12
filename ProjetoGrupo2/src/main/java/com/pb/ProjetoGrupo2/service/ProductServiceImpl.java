package com.pb.ProjetoGrupo2.service;



import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDto save (ProductFormDto productFormDto){
        return repository.save(ProductDto);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Optional<Product> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
