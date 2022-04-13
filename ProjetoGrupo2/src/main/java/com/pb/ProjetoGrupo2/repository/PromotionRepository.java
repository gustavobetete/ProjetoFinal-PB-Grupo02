package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
