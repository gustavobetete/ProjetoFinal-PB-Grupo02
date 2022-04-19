package com.pb.ProjetoGrupo2.builder;

import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.PromotionFormDto;
import com.pb.ProjetoGrupo2.entities.Promotion;

import java.math.BigDecimal;

public class PromotionBuilder {


    public static Promotion getPromotion() {

        Promotion promotion = new Promotion();
        promotion.setId(1L);
        promotion.setDescription("-50% Salgado");
        promotion.setPromotion_price(BigDecimal.valueOf(0.5));
        return promotion;
    }

    public static Promotion getPromotionTwo() {
        Promotion promotionTwo = new Promotion();
        promotionTwo.setId(2L);
        promotionTwo.setDescription("-40% Doce");
        promotionTwo.setPromotion_price(BigDecimal.valueOf(0.4));
        return promotionTwo;
    }

    public static PromotionDto getPromotionDto() {

        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setId(1L);
        promotionDto.setDescription("-50% Salgado");
        promotionDto.setPromotion_price(BigDecimal.valueOf(0.5));
        return promotionDto;
    }

    public static PromotionDto getPromotionDtoTwo(){
        PromotionDto promotionDtoTwo = new PromotionDto();
        promotionDtoTwo.setId(2L);
        promotionDtoTwo.setDescription("-50% Assados");
        promotionDtoTwo.setPromotion_price(BigDecimal.valueOf(0.50));
        return promotionDtoTwo;
    }

    public static PromotionFormDto getPromotionFormDto() {
        PromotionFormDto promotionFormDto = new PromotionFormDto();
        promotionFormDto.setPromotion_price(BigDecimal.valueOf(0.4));
        promotionFormDto.setDescription("-40% Doce");
        return promotionFormDto;
    }
}
