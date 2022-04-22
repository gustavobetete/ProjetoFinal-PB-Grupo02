package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductOrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
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
    private OrderRepository orderRepository;

    @Autowired
<<<<<<< HEAD
=======
    private ProductRepository productRepository;

    @Autowired
>>>>>>> e291120593c8544b18e2f5559920660ccbe2383b
    private ModelMapper modelMapper;

    @Override
    public Page<OrderDto> findAll(Pageable page){
        Page<Order> orders = this.orderRepository.findAll(page);
        List<OrderDto> listOrders = orders.stream().map(order ->
                modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return new PageImpl<OrderDto>(listOrders, page, orders.getTotalElements());
    }

    @Override
    public OrderDto findById(Long id){
        Optional<Order> orders = orderRepository.findById(id);
        if (orders.isPresent()){
            return modelMapper.map(orders.get(), OrderDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public OrderDto save(OrderFormDto orderFormDto){
        Order order = this.orderRepository.save(modelMapper.map(orderFormDto, Order.class));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(Long id, OrderFormDto orderFormDto) {
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isPresent()) {
            Order orderUpdated = modelMapper.map(orderFormDto, Order.class);
            orderUpdated.setId(id);
            orderRepository.save(orderUpdated);
            return modelMapper.map(orderUpdated, OrderDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public List<ProductDto> listAllProduct(Long id){
        List<Product> products = productRepository.findByOrdersId(id);
        List<ProductDto> productDto = products.stream().map(i -> modelMapper.map(i, ProductDto.class)).collect(Collectors.toList());
        return productDto;
    }
}
