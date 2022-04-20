package com.pb.ProjetoGrupo2.services;

import com.pb.ProjetoGrupo2.builder.ProductBuilder;
import com.pb.ProjetoGrupo2.builder.PromotionBuilder;
import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.PromotionFormDto;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.entities.Promotion;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.repository.PromotionRepository;
import com.pb.ProjetoGrupo2.service.ProductServiceImpl;
import com.pb.ProjetoGrupo2.service.PromotionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Promotion Service")
public class PromotionServiceTest {

    @Autowired
    private PromotionServiceImpl promotionService;

    @MockBean
    private PromotionRepository repository;

    @Test
    @DisplayName("Save promotion")
    public void savePromotion() {
        Promotion promotion = PromotionBuilder.getPromotion();

        when(this.repository.save(any(Promotion.class))).thenReturn(promotion);

        PromotionDto promotionDTO = this.promotionService.save(PromotionBuilder.getPromotionFormDto());

        assertThat(promotionDTO.getId()).isNotNull();
        assertThat(promotionDTO.getPromotionPrice()).isEqualTo(promotion.getPromotionPrice());
        assertThat(promotionDTO.getDescription()).isEqualTo(promotion.getDescription());

    }

    @Test
    @DisplayName("List all promotion")
    public void listPromotion() {
        Promotion promotion = PromotionBuilder.getPromotion();

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Promotion> promotions = Arrays.asList(promotion);
        Page<Promotion> page = new PageImpl<>(promotions, pageRequest, 1);

        when(this.repository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<PromotionDto> pagePromotionDTO = this.promotionService.findAll(pageRequest);

        assertThat(pagePromotionDTO.getContent()).hasSize(1);
        assertThat(pagePromotionDTO.getTotalPages()).isEqualTo(1);
        assertThat(pagePromotionDTO.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("FindById promotion")
    public void findByIdPromotion() {
        Promotion promotion = PromotionBuilder.getPromotion();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(promotion));

        PromotionDto promotionDTO = this.promotionService.findById(promotion.getId());

        assertThat(promotionDTO.getId()).isNotNull();
        assertThat(promotionDTO.getPromotionPrice()).isEqualTo(promotion.getPromotionPrice());
        assertThat(promotionDTO.getDescription()).isEqualTo(promotion.getDescription());

    }

    @Test
    @DisplayName("findById promotion not found")
    public void findByIdPromotion_NotFound() {
        Promotion promotion = PromotionBuilder.getPromotion();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.promotionService.findById(promotion.getId()));
    }


    @Test
    @DisplayName("Update promotion")
    public void updatePromotion() {
        Promotion promotion = PromotionBuilder.getPromotion();
        PromotionFormDto promotionFormDTO = PromotionBuilder.getPromotionFormDto();
        promotionFormDTO.setPromotionPrice(BigDecimal.valueOf(0.4));

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(promotion));
        when(this.repository.save(any(Promotion.class))).thenReturn(promotion);

        PromotionDto promotionDTO = this.promotionService.update(promotion.getId(), promotionFormDTO);

        assertThat(promotionDTO.getId()).isNotNull();
        assertThat(promotionDTO.getPromotionPrice()).isEqualTo(promotionFormDTO.getPromotionPrice());
        assertThat(promotionDTO.getDescription()).isEqualTo(promotionFormDTO.getDescription());

    }

    @Test
    @DisplayName("Update promotion not found")
    public void updatePromotion_NotFound() {
        Promotion promotion = PromotionBuilder.getPromotion();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.promotionService.update(promotion.getId(), PromotionBuilder.getPromotionFormDto()));
    }

    @Test
    @DisplayName("Delete promotion")
    public void deletePromotion() {
        Promotion promotion = PromotionBuilder.getPromotion();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(promotion));

        this.promotionService.deleteById(1L);

        verify(this.repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete promotion not found")
    public void deletePromotion_NotFound() {
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.promotionService.deleteById(1L));
    }
}
