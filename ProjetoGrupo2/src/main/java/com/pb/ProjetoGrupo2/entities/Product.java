package com.pb.ProjetoGrupo2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pb.ProjetoGrupo2.constants.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Double unitPrice;
    private Integer quantity;
<<<<<<< HEAD
    private Integer virtualQuantity = quantity;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "product")
    @JsonIgnore
    private List<Promotion> promotion;
=======

>>>>>>> dev-gustavo

}
