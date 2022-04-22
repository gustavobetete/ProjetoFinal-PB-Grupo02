package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> e291120593c8544b18e2f5559920660ccbe2383b
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrdersId(Long id);
}
