package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.PromotionFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PromotionService {

    Page<PromotionDto> findAll(Pageable page);

    PromotionDto findById(Long id);

    PromotionDto save(PromotionFormDto promotionFormDto);

    PromotionDto update(Long id, PromotionFormDto promotionFormDto);

    ResponseEntity<Object> deleteById(Long id);
}
