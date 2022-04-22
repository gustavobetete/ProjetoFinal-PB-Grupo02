package com.pb.ProjetoGrupo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany//(mappedBy = "user")
    @JoinTable(name = "users_id", joinColumns = @JoinColumn(name = "users_orders"), inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> orders;
}
