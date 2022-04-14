package com.pb.ProjetoGrupo2.controller;

import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.PromotionFormDto;
import com.pb.ProjetoGrupo2.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    PromotionService service;

    @GetMapping
    public ResponseEntity<Page<PromotionDto>> findAll(@PageableDefault(page = 0, size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable page){
        Page<PromotionDto> promotion = this.service.findAll(page);
        return ResponseEntity.ok(promotion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PromotionDto> save(@RequestBody @Valid PromotionFormDto promotionFormDto){
        PromotionDto promoDto = this.service.save(promotionFormDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(promoDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PromotionDto> update(@PathVariable Long id, @RequestBody @Valid PromotionFormDto promoFormDto) {
        PromotionDto promotionDto = this.service.update(id, promoFormDto);
        return ResponseEntity.ok(promotionDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
