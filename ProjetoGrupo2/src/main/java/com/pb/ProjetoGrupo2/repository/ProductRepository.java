package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import javax.transaction.Transactional;

@Transactional
=======
>>>>>>> a619e47e734eaa2b3cf18a322b562d7ae3b30baa
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByType(Type valueOf, Pageable pageable);
}
