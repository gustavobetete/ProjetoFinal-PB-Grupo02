package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.OrderDto;
import com.pb.ProjetoGrupo2.dto.OrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public OrderDto save(OrderFormDto orderFormDto) throws Exception{
        Order order = modelMapper.map(orderFormDto, Order.class);
        order.setId(null);

        Order orderCreated = createOrder(orderFormDto, order);

        this.orderRepository.save(orderCreated);
        return modelMapper.map(orderCreated, OrderDto.class);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);

            String idOrder = order.get().getId().toString();
            return String.format("Order %s deleted with success!", idOrder);
        }
        throw new ObjectNotFoundException("Order not found!");
    }


    private Order createOrder(OrderFormDto orderFormDto, Order order) throws Exception {
        Double TotalValue = (double) 0;

        for(int i = 0; i < order.getProducts().size(); i++ ){
            Optional<Product> optionalProduct = this.productRepository.findById(orderFormDto.getProducts().get(i).getProductId());

            if(optionalProduct.isPresent()){
                Product product = optionalProduct.get();
                if(product.getQuantity() < orderFormDto.getProducts().get(i).getQuantity())throw new Exception("Insufficient quantity in stock");

                order.getProducts().get(i).setName(product.getName());
                order.getProducts().get(i).setUnitPrice(product.getUnitPrice());
                order.getProducts().get(i).setType(product.getType());
                order.getProducts().get(i).setQuantity(orderFormDto.getProducts().get(i).getQuantity());

                product.setQuantity(product.getQuantity() - orderFormDto.getProducts().get(i).getQuantity());
                this.productRepository.save(product);

                TotalValue += order.getProducts().get(i).getUnitPrice() * order.getProducts().get(i).getQuantity();

            }else{
                throw new Exception("Product not found!");
            }

        }
        order.setTotal(TotalValue);
        return order;
    }
}
