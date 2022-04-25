package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrdersId(Long id);
}
