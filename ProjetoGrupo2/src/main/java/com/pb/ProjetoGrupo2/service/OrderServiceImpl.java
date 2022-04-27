package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
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
    private ProductRepository productRepository;


    @Autowired
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
    public OrderDto save(OrderFormDto orderFormDto) {

        Order order = modelMapper.map(orderFormDto, Order.class);
        order.setId(null);
        createOrder(orderFormDto, order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(Long id, OrderFormDto orderFormDto) {
        Optional<Order> order = this.orderRepository.findById(id);
        if(order.isPresent()) {
            Order orderUpdated = modelMapper.map(orderFormDto, Order.class);
            orderUpdated.setId(id);
            createOrder(orderFormDto, orderUpdated);

            orderRepository.save(orderUpdated);
            return modelMapper.map(orderUpdated, OrderDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public String deleteById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);

            String idOrder = order.get().getId().toString();
            return "Order " + idOrder + " deleted with success!";
        }
        throw new ObjectNotFoundException("Order not found!");
    }


    private void createOrder(OrderFormDto orderFormDto, Order order) {
        Double TotalValue = (double) 0;

        for(int i = 0; i < order.getProducts().size(); i++ ){
            Optional<Product> product = this.productRepository.findById(orderFormDto.getProducts().get(i).getProductId());

            if(product.isPresent()){
                order.getProducts().get(i).setName(product.get().getName());
                order.getProducts().get(i).setUnitPrice(product.get().getUnitPrice());
                order.getProducts().get(i).setType(product.get().getType());

                TotalValue += order.getProducts().get(i).getUnitPrice() * order.getProducts().get(i).getQuantity();
            }
        }
        order.setTotal(TotalValue);

    }
}
