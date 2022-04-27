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
    public OrderDto save(OrderFormDto orderFormDto){
        Order order = modelMapper.map(orderFormDto, Order.class);
        order.setId(null);

<<<<<<< HEAD
        Double somaTotal = (double) 0;
        BigDecimal totalPrice = null;

        for(int i = 0; i < order.getProducts().size(); i++ ){
            Optional<Product> product = this.productRepository.findById(orderFormDto.getProducts().get(i).getProductId());

            if(product.isPresent()){
                order.getProducts().get(i).setName(product.get().getName());
                order.getProducts().get(i).setUnitPrice(product.get().getUnitPrice());
                order.getProducts().get(i).setType(product.get().getType());
                order.getProducts().get(i).setQuantity(product.get().getQuantity());
                order.setProductTotal(BigDecimal.valueOf((order.getProducts().get(i).getQuantity()) * (order.getProducts().get(i).getUnitPrice())));
                somaTotal += order.getProducts().get(i).getUnitPrice();
            }
                //Se esta em promoção... promotion type = FRITO && SalgadoAtual.FRITO = preço salgado atual - promoção
        }
        order.setTotal(somaTotal);
=======
>>>>>>> 7c3c2ae... Arrumando endpoint save do order
        createOrder(orderFormDto, order);

        this.orderRepository.save(order);
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
        return null;
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
            //Se esta em promoção... promotion type = FRITO && SalgadoAtual.FRITO = preço salgado atual - promoção
        }
        order.setTotal(TotalValue);

    }
}
