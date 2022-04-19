package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<OrderDto> findAll(Pageable page){
        Page<Order> orders = this.repository.findAll(page);
        List<OrderDto> listOrders = orders.stream().map(order -> mapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<OrderDto>(listOrders, page, orders.getTotalElements());
    }

    @Override
    public OrderDto findById(Long id){
        Optional<Order> orders = repository.findById(id);
        if (orders.isPresent()){
            return mapper.map(orders.get(), OrderDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public OrderDto save(OrderFormDto orderFormDto){
        Order order = this.repository.save(mapper.map(orderFormDto, Order.class));
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(Long id, OrderFormDto orderFormDto) {
        Optional<Order> order = this.repository.findById(id);
        if(order.isPresent()) {
            Order orderUpdated = mapper.map(orderFormDto, Order.class);
            orderUpdated.setId(id);
            repository.save(orderUpdated);
            return mapper.map(orderUpdated, OrderDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<Order> order = repository.findById(id);
        if(order.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Order not found!");
    }
}
