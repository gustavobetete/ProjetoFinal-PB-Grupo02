package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.constants.Status;
import com.pb.ProjetoGrupo2.dto.OrderDTO;
import com.pb.ProjetoGrupo2.dto.OrderFormDTO;
import com.pb.ProjetoGrupo2.dto.OrderedProductDTO;
import com.pb.ProjetoGrupo2.dto.StatusUpdateFormDTO;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.entities.OrderedProduct;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.entities.User;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import com.pb.ProjetoGrupo2.repository.OrderedProductRepository;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private UserRepository userRepository;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDTO postOrder(OrderFormDTO orderFormDTO) {

        Optional<User> optionalUser = userRepository.findById(orderFormDTO.getUserId());

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            Order order = new Order(LocalDateTime.now(), user);
            orderRepository.save(order);
            user.getOrders().add(order);
            userRepository.save(user);
            return modelMapper.map(order, OrderDTO.class);
        }
        return null;
    }

    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderDTO> orderDTOList =
                orders.stream().map(o -> modelMapper.map(o, OrderDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(orderDTOList, pageable, orderDTOList.size());
    }

    @Override
    public Page<OrderedProductDTO> getOrderProduct(Long orderId, Pageable pageable) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()){
            Page<OrderedProduct> orderedProducts = orderedProductRepository.findByOrderId(orderId, pageable);
            List<OrderedProductDTO> orderedProductDTOList =
                    orderedProducts.stream().map(o ->
                            modelMapper.map(o, OrderedProductDTO.class)).collect(Collectors.toList());
            return new PageImpl<>(orderedProductDTOList, pageable, orderedProductDTOList.size());
        }
        return null;
    }

    @Override
    public OrderDTO putOrderStatus(Long orderId, StatusUpdateFormDTO statusUpdateFormDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent() && optionalOrder.get().getStatus().equals(Status.OPEN)){
            Order order = optionalOrder.get();
            order.setStatus(statusUpdateFormDTO.getStatus());
            orderRepository.save(order);

            if (order.getStatus().equals(Status.NOT_WITHDRAWN)){
                List<OrderedProduct> orderedProducts = orderedProductRepository.findByOrderId(orderId);
                for (int i = 0; i < orderedProducts.size(); i++) {
                    Product product = orderedProducts.get(i).getProduct();
                    product.setQuantity(product.getQuantity() + 1);
                    productRepository.save(product);
                }
                return modelMapper.map(order, OrderDTO.class);
            }
        }
        return null;
    }
}
