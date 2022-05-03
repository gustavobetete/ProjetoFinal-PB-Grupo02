package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.constants.OrderStatus;
import com.pb.ProjetoGrupo2.constants.UserStatus;
import com.pb.ProjetoGrupo2.dto.*;
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

    public OrderDTO postOrder(OrderFormDTO orderFormDTO) {

        Optional<User> optionalUser = userRepository.findById(orderFormDTO.getUserId());

        if (optionalUser.isPresent() && optionalUser.get().getStatus().equals(UserStatus.ACTIVE)){
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
    public OrderedProductDTO postProductIntoOrder
            (Long userId, Long orderId, OrderedProductFormDTO orderedProductFormDTO) {

        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Product> optionalProduct = productRepository.findById(orderedProductFormDTO.getProductId());

        if (optionalUser.isPresent() && optionalOrder.isPresent() && optionalProduct.isPresent()){

            Order order = optionalOrder.get();
            Product product = optionalProduct.get();

            OrderedProduct orderedProduct = null;
            int minusQuantity = 0;

            if(order.getStatus().equals(OrderStatus.WITHDRAWN) || order.getStatus().equals(OrderStatus.NOT_WITHDRAWN)){
                return null;
            }

            if(product.getQuantity() < orderedProductFormDTO.getOrderedQuantity()){
                return null;
            }

            for (int i = 0; i < orderedProductFormDTO.getOrderedQuantity(); i++) {
                orderedProduct =
                        new OrderedProduct(product.getName(), product.getType(),1,
                                product.getUnityPrice(), order, product);
                order.setTotalPrice(order.getTotalPrice().add(product.getUnityPrice()));
                orderRepository.save(order);
                orderedProductRepository.save(orderedProduct);
                minusQuantity++;
            }
            product.setQuantity(product.getQuantity() - minusQuantity);
            productRepository.save(product);
            return modelMapper.map(orderedProduct, OrderedProductDTO.class);
        }
        return  null;
    }

    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        List<OrderDTO> orderDTOList =
                orders.stream().map(o -> modelMapper.map(o, OrderDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(orderDTOList, pageable, orderDTOList.size());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            return modelMapper.map(optionalOrder.get(), OrderDTO.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    public Page<OrderForUserDTO> getUserOrders(Long userId, Pageable pageable) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()){
            Page<Order> orders = orderRepository.findByUserId(userId, pageable);
            List<OrderForUserDTO> userOrderDTOList =
                    orders.stream().map(u -> modelMapper.map(u, OrderForUserDTO.class)).collect(Collectors.toList());
            return new PageImpl<>(userOrderDTOList, pageable, userOrderDTOList.size());
        }
        return null;
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
    public OrderDTO putOrderStatus(Long orderId, OrderStatusUpdateFormDTO statusUpdateFormDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent() && optionalOrder.get().getStatus().equals(OrderStatus.OPEN)) {
            Order order = optionalOrder.get();
            order.setStatus(statusUpdateFormDTO.getStatus());
            orderRepository.save(order);
            if (order.getStatus().equals(OrderStatus.NOT_WITHDRAWN)){
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

    @Override
    public String deleteProductFromUserOrder(Long orderId, Long orderedId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<OrderedProduct> optionalOrderedProduct = orderedProductRepository.findById(orderedId);

        if(optionalOrder.isPresent() && optionalOrderedProduct.isPresent()){

            if (!optionalOrder.get().getStatus().equals(OrderStatus.OPEN)){
                throw new RuntimeException("Order is closed, so you can't edit");
            }

            OrderedProduct orderedProduct = optionalOrderedProduct.get();
            Product product = optionalOrderedProduct.get().getProduct();
            Order order = optionalOrder.get();

            order.setTotalPrice(order.getTotalPrice().subtract(orderedProduct.getUnityPrice()));
            orderRepository.save(order);
            orderedProductRepository.deleteById(orderedId);
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);

            return "1x produto: " + orderedProduct.getName() + " foi retirado do seu pedido: " + order.getId();

        }else {
            throw new RuntimeException("No Content");
        }

    }
}
