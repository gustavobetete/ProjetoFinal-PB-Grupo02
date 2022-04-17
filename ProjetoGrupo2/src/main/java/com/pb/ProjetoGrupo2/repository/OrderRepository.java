package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
