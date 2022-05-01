package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.constants.Status;
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
    private ProductRepository productRepository;
    @Autowired
    private OrderedProductRepository orderedProductRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO postUser(UserFormDTO userFormDto) {
        User user = userRepository.save(modelMapper.map(userFormDto, User.class));
        return modelMapper.map(user, UserDTO.class);
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

            if(order.getStatus().equals(Status.WITHDRAWN) || order.getStatus().equals(Status.NOT_WITHDRAWN)){
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
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserDTO> userDTOList =
                users.stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(userDTOList, pageable, userDTOList.size());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return modelMapper.map(optionalUser.get(), UserDTO.class);
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
    public UserDTO putUser(Long id, UpdatedUserFormDTO updatedUserFormDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User userUpdated = modelMapper.map(updatedUserFormDTO, User.class);
            userUpdated.setId(id);
            userRepository.save(userUpdated);
            return modelMapper.map(userUpdated, UserDTO.class);
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
    public String deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            String idUser = user.get().getId().toString();
            return "User " + idUser + " deleted with success";
        }
        throw new ObjectNotFoundException("User not found!");
    }

    @Override
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
    public String deleteProductFromUserOrder(Long orderId, Long orderedId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<OrderedProduct> optionalOrderedProduct = orderedProductRepository.findById(orderedId);

        if(optionalOrder.isPresent() && optionalOrderedProduct.isPresent() &&
                optionalOrder.get().getStatus().equals(Status.OPEN)){

            OrderedProduct orderedProduct = optionalOrderedProduct.get();
            Product product = optionalOrderedProduct.get().getProduct();
            Order order = optionalOrder.get();

            order.setTotalPrice(order.getTotalPrice().subtract(orderedProduct.getUnityPrice()));
            orderRepository.save(order);
            orderedProductRepository.deleteById(orderedId);
            product.setQuantity(product.getQuantity() + 1);
            productRepository.save(product);

            return "1x produto: " + orderedProduct.getName() + " foi retirado do seu pedido: " + order.getId();
        }
        return null;
    }
}

