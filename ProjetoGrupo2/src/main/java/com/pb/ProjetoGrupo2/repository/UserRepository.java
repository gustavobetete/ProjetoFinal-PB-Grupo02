package com.pb.ProjetoGrupo2.repository;

import com.pb.ProjetoGrupo2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
=======
import java.util.Optional;

>>>>>>> a619e47e734eaa2b3cf18a322b562d7ae3b30baa
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
