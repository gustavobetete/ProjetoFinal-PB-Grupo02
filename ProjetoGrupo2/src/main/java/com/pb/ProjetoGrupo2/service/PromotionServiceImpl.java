package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.PromotionFormDto;
import com.pb.ProjetoGrupo2.entities.Promotion;
import com.pb.ProjetoGrupo2.repository.PromotionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService{

    @Autowired
    private PromotionRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<PromotionDto> findAll(Pageable page){
        Page<Promotion> promotion = this.repository.findAll(page);
        List<PromotionDto> listPromotion = promotion.getContent().stream().map(p -> mapper.map(p, PromotionDto.class)).collect(Collectors.toList());
        return new PageImpl<PromotionDto>(listPromotion, page, promotion.getTotalElements());
    }

    @Override
    public PromotionDto findById(Long id){
        Optional<Promotion> promotion = repository.findById(id);
        if (promotion.isPresent()){
            return mapper.map(promotion.get(), PromotionDto.class);
        }
        throw new ObjectNotFoundException("Promotion not found!");
    }

    @Override
    public PromotionDto save(PromotionFormDto promotionFormDto){
        Promotion promotion = this.repository.save(mapper.map(promotionFormDto, Promotion.class));
        return mapper.map(promotion, PromotionDto.class);
    }

    @Override
    public PromotionDto update(Long id, PromotionFormDto promotionFormDto) {
        Optional<Promotion> promotion = this.repository.findById(id);
        if(promotion.isPresent()) {
            Promotion promotionUpdated = mapper.map(promotionFormDto, Promotion.class);
            promotionUpdated.setId(id);
            repository.save(promotionUpdated);
            return mapper.map(promotionUpdated, PromotionDto.class);
        }
        throw new ObjectNotFoundException("Promotion not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<Promotion> promo = repository.findById(id);
        if(promo.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Promotion not found!");
    }
}
