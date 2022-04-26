//package com.pb.ProjetoGrupo2.services;
//
//import com.pb.ProjetoGrupo2.builder.OrderBuilder;
//import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
//import com.pb.ProjetoGrupo2.dto.OrderDto;
//import com.pb.ProjetoGrupo2.dto.OrderFormDto;
//import com.pb.ProjetoGrupo2.entities.Order;
//import com.pb.ProjetoGrupo2.repository.OrderRepository;
//import com.pb.ProjetoGrupo2.repository.UserRepository;
//import com.pb.ProjetoGrupo2.service.OrderServiceImpl;
//import com.pb.ProjetoGrupo2.service.UserServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DisplayName("Tests for Order Service")
//public class OrderServiceTest {
//
//    @Autowired
//    private OrderServiceImpl orderService;
//
//    @MockBean
//    private OrderRepository repository;
//
//    @MockBean
//    private UserServiceImpl users;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    @DisplayName("Save order")
//    public void saveOrder() {
//        Order order = OrderBuilder.getOrder();
//
//        when(this.repository.save(any(Order.class))).thenReturn(order);
//
//        OrderDto orderDTO = this.orderService.save(OrderBuilder.getOrderFormDto());
//
//        assertThat(orderDTO.getDeliveryDate()).isEqualTo(order.getDeliveryDate());
//        assertThat(orderDTO.getQuantity()).isEqualTo(order.getQuantity());
//        assertThat(orderDTO.getPurchaseDate()).isEqualTo(order.getPurchaseDate());
//        assertThat(orderDTO.getIdUser()).isEqualTo(order.getUser().getId());
//    }
//
//    @Test
//    @DisplayName("List all orders")
//    public void listOrders() {
//        Order order = OrderBuilder.getOrder();
//
//        PageRequest pageRequest = PageRequest.of(0, 10);
//        List<Order> orders = Arrays.asList(order);
//        Page<Order> page = new PageImpl<>(orders, pageRequest, 1);
//
//        when(this.repository.findAll(any(PageRequest.class))).thenReturn(page);
//
//        Page<OrderDto> pageOrderDTO = this.orderService.findAll(pageRequest);
//
//        assertThat(pageOrderDTO.getContent()).hasSize(1);
//        assertThat(pageOrderDTO.getTotalPages()).isEqualTo(1);
//        assertThat(pageOrderDTO.getTotalElements()).isEqualTo(1);
//    }
//
//    @Test
//    @DisplayName("FindById orders")
//    public void findByIdOrder() {
//        Order order = OrderBuilder.getOrder();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.of(order));
//
//        OrderDto orderDTO = this.orderService.findById(order.getId());
//
//        assertThat(orderDTO.getId()).isNotNull();
//        assertThat(orderDTO.getDeliveryDate()).isEqualTo(order.getDeliveryDate());
//        assertThat(orderDTO.getPurchaseDate()).isEqualTo(order.getPurchaseDate());
//        assertThat(orderDTO.getQuantity()).isEqualTo(order.getQuantity());
//
//    }
//
//    @Test
//    @DisplayName("findById Order not found")
//    public void findByIdOrder_NotFound() {
//        Order order = OrderBuilder.getOrder();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> this.orderService.findById(order.getId()));
//    }
//
//
//    @Test
//    @DisplayName("Update order")
//    public void updateOrder() {
//        Order order = OrderBuilder.getOrder();
//        OrderFormDto orderFormDTO = OrderBuilder.getOrderFormDto();
//        orderFormDTO.setQuantity(2);
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.of(order));
//        when(this.repository.save(any(Order.class))).thenReturn(order);
//
//        OrderDto orderDTO = this.orderService.update(order.getId(), orderFormDTO);
//
//        assertThat(orderDTO.getId()).isNotNull();
//        assertThat(orderDTO.getDeliveryDate()).isEqualTo(orderFormDTO.getDeliveryDate());
//        assertThat(orderDTO.getPurchaseDate()).isEqualTo(orderFormDTO.getPurchaseDate());
//        assertThat(orderDTO.getQuantity()).isEqualTo(orderFormDTO.getQuantity());
//
//    }
//
//    @Test
//    @DisplayName("Update order not found")
//    public void updateOrder_NotFound() {
//        Order order = OrderBuilder.getOrder();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> this.orderService.update(order.getId(), OrderBuilder.getOrderFormDto()));
//    }
//
//    @Test
//    @DisplayName("Delete order")
//    public void deleteOrder() {
//        Order order = OrderBuilder.getOrder();
//
//        when(this.repository.findById(anyLong())).thenReturn(Optional.of(order));
//
//        this.orderService.deleteById(1L);
//
//        verify(this.repository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    @DisplayName("Delete order not found")
//    public void deleteOrder_NotFound() {
//        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatExceptionOfType(ObjectNotFoundException.class)
//                .isThrownBy(() -> this.orderService.deleteById(1L));
//    }
//}
