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
    private PromotionRepository promotionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PromotionDto> findAll(Pageable page){
        Page<Promotion> promotions = this.promotionRepository.findAll(page);
        List<PromotionDto> listPromotion = promotions.getContent().stream().map(promotion ->
                modelMapper.map(promotion, PromotionDto.class)).collect(Collectors.toList());
        return new PageImpl<PromotionDto>(listPromotion, page, promotions.getTotalElements());
    }

    @Override
    public PromotionDto findById(Long id){
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if (promotion.isPresent()){
            return modelMapper.map(promotion.get(), PromotionDto.class);
        }
        throw new ObjectNotFoundException("Promotion not found!");
    }

    @Override
    public PromotionDto save(PromotionFormDto promotionFormDto){
        Promotion promotion = this.promotionRepository.save(modelMapper.map(promotionFormDto, Promotion.class));
        return modelMapper.map(promotion, PromotionDto.class);
    }

    @Override
    public PromotionDto update(Long id, PromotionFormDto promotionFormDto) {
        Optional<Promotion> promotion = this.promotionRepository.findById(id);
        if(promotion.isPresent()) {
            Promotion promotionUpdated = modelMapper.map(promotionFormDto, Promotion.class);
            promotionUpdated.setId(id);
            promotionRepository.save(promotionUpdated);
            return modelMapper.map(promotionUpdated, PromotionDto.class);
        }
        throw new ObjectNotFoundException("Promotion not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<Promotion> promo = promotionRepository.findById(id);
        if(promo.isPresent()){
            promotionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Promotion not found!");
    }
}
