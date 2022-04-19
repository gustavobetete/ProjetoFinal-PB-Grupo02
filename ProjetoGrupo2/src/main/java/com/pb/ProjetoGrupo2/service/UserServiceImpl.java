package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.PromotionDto;
import com.pb.ProjetoGrupo2.dto.UserDto;
import com.pb.ProjetoGrupo2.dto.UserFormDto;
import com.pb.ProjetoGrupo2.entities.Promotion;
import com.pb.ProjetoGrupo2.entities.User;
import com.pb.ProjetoGrupo2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<UserDto> findAll(Pageable page) {
        Page<User> users = this.repository.findAll(page);
        List<UserDto> usersList = users.stream().map(product -> mapper.map(product, UserDto.class)).collect(Collectors.toList());
        return new PageImpl<UserDto>(usersList, page, users.getTotalElements());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()){
            return mapper.map(user.get(), UserDto.class);
        }
        throw new ObjectNotFoundException("User not found!");
    }


    @Override
    public UserDto save(UserFormDto userFormDto) {
            User user = this.repository.save(mapper.map(userFormDto, User.class));
            return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto update(Long id, UserFormDto userFormDto) {
        Optional<User> user = this.repository.findById(id);
        if(user.isPresent()) {
            User userUpdated = mapper.map(userFormDto, User.class);
            userUpdated.setId(id);
            repository.save(userUpdated);
            return mapper.map(userUpdated, UserDto.class);
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("User not found!");
    }

}
