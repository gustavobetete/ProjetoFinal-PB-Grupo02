package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.UserDto;
import com.pb.ProjetoGrupo2.dto.UserFormDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserDto> findAll(Pageable page);

    UserDto findById(Long id);

    UserDto save(UserFormDto userFormDto);

    UserDto update(Long id, UserFormDto userFormDto);

    Object deleteById(Long id);
}
