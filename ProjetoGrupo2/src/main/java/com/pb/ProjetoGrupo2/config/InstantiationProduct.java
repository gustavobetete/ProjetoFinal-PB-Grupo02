package com.pb.ProjetoGrupo2.config;

import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class InstantiationProduct implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll(); //Limpar a coleção

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

//        Usuario maria = new Usuario(null, "Maria Clara", "maria@email.com", "$2a$10$LuHsIBeXLf/lh.9WnF2LbO2PNc9D2SyPG2j4c3xIufP3uVs0rsyLm", null);
//        Usuario leticia = new Usuario(null, "Leticia Buffoni", "leticia@email.com", "$2a$10$LuHsIBeXLf/lh.9WnF2LbO2PNc9D2SyPG2j4c3xIufP3uVs0rsyLm", null);
//        Usuario marcos = new Usuario(null, "Marcos Cleyton", "marcos@email.com", "$2a$10$LuHsIBeXLf/lh.9WnF2LbO2PNc9D2SyPG2j4c3xIufP3uVs0rsyLm", null);
//
//        usuarioRepository.saveAll(Arrays.asList(maria, leticia, marcos));

        Product coxinha = new Product(null, "Coxinha", Type.FRITO, BigDecimal.valueOf(7.00), 10);
        Product Mistao = new Product(null, "Mistao", Type.ASSADO, BigDecimal.valueOf(5.00), 8);
        Product CocaCola = new Product(null, "Coca-Cola", Type.BEBIDA, BigDecimal.valueOf(3.50), 20);
        Product Lanche_Natural = new Product(null, "Lanche_Natural", Type.NATURAL, BigDecimal.valueOf(9.00), 5);
        Product BomBom = new Product(null, "BomBom", Type.DOCE, BigDecimal.valueOf(2.00), 12);

        productRepository.saveAll(Arrays.asList(coxinha, Mistao, CocaCola, Lanche_Natural, BomBom));

    }
}