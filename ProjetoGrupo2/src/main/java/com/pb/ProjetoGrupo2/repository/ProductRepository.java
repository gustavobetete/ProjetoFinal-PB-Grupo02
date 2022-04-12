package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   // void delete(Optional<Product> product);

}
