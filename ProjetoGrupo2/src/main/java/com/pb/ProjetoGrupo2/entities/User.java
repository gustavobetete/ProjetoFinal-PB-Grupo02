package com.pb.ProjetoGrupo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return this.profiles;
    }

    //
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override //verifica se a conta esta expirada
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //verifica se a conta não esta bloqueada
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override //verifica se a credencial não esta expirada
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override//verifica se esta hgabilitada
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
