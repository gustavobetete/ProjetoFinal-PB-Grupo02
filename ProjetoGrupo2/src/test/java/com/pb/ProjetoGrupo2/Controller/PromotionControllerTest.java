package com.pb.ProjetoGrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.ProjetoGrupo2.builder.PromotionBuilder;
import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.PromotionFormDto;
import com.pb.ProjetoGrupo2.entities.Promotion;
import com.pb.ProjetoGrupo2.service.PromotionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PromotionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PromotionService promotionService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void postPromotion() throws Exception{

        Promotion promotion = PromotionBuilder.getPromotion();
        PromotionDto promotionDto = PromotionBuilder.getPromotionDto();

        when(promotionService.save(any())).thenReturn(promotionDto);
        mockMvc.perform(post("/promotion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(promotion))).andExpect(status().isCreated());

    }

    @Test
    void getPromotions() throws Exception{

        PromotionDto promotionDto = PromotionBuilder.getPromotionDto();
        PromotionDto promotionDtoTwo = PromotionBuilder.getPromotionDtoTwo();

        List<PromotionDto> promotionDtoList = new ArrayList<>(
                Arrays.asList(promotionDto, promotionDtoTwo)
        );

        PageRequest pageRequest = PageRequest.of(0, 5);

        Page<PromotionDto> promotionDtoPage =
                new PageImpl<>(promotionDtoList, pageRequest, promotionDtoList.size());

        when(promotionService.findAll(any(PageRequest.class))).thenReturn(promotionDtoPage);

        mockMvc.perform(get("/promotion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void getPromotionById() throws Exception{

        Promotion promotion = PromotionBuilder.getPromotion();
        PromotionDto promotionDto = PromotionBuilder.getPromotionDto();

        when(promotionService.findById(promotion.getId())).thenReturn(promotionDto);

        long id = 1;

        mockMvc.perform(get("/promotion/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(promotionDto.getDescription()))
                .andDo(print());

    }

    //*****************FAZER TESTE DO PUT*****************

    @Test
    void deletePromotion() throws Exception{

        Promotion promotion = PromotionBuilder.getPromotion();
        PromotionDto promotionDto = PromotionBuilder.getPromotionDto();

        when(promotionService.deleteById(promotion.getId())).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/promotion/1")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(promotionDto)))
                .andExpect(status().isOk()).andDo(print());
    }
}
