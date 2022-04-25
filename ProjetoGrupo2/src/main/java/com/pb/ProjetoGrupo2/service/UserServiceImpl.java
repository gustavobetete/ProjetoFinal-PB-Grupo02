package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.UserDto;
import com.pb.ProjetoGrupo2.dto.UserFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.entities.User;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
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
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<UserDto> findAll(Pageable page) {
        Page<User> users = this.userRepository.findAll(page);
        List<UserDto> usersList = users.stream().map(product ->
                modelMapper.map(product, UserDto.class)).collect(Collectors.toList());
        return new PageImpl<UserDto>(usersList, page, users.getTotalElements());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
    public UserDto save(UserFormDto userFormDto) {
            User user = this.userRepository.save(modelMapper.map(userFormDto, User.class));
            return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto update(Long id, UserFormDto userFormDto) {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()) {
            User userUpdated = modelMapper.map(userFormDto, User.class);
            userUpdated.setId(id);
            userRepository.save(userUpdated);
            return modelMapper.map(userUpdated, UserDto.class);
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
    public List<OrderDto> listAllOrders(Long id){
        List<Order> orders = orderRepository.findByUserId(id);
        List<OrderDto> orderDto = orders.stream().map(i -> modelMapper.map(i, OrderDto.class)).collect(Collectors.toList());
        return orderDto;
    }
}
