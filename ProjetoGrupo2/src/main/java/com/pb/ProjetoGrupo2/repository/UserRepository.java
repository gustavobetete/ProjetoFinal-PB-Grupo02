package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
