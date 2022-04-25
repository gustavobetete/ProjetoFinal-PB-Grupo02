package com.pb.ProjetoGrupo2.services;

import com.pb.ProjetoGrupo2.builder.CartBuilder;
import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.CartFormDto;
import com.pb.ProjetoGrupo2.entities.Cart;
import com.pb.ProjetoGrupo2.repository.CartRepository;
import com.pb.ProjetoGrupo2.repository.UserRepository;
import com.pb.ProjetoGrupo2.service.CartServiceImpl;
import com.pb.ProjetoGrupo2.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Order Service")
public class CartServiceTest {

    @Autowired
    private CartServiceImpl orderService;

    @MockBean
    private CartRepository repository;

    @MockBean
    private UserServiceImpl users;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Save order")
    public void saveOrder() {
        Cart cart = CartBuilder.getOrder();

        when(this.repository.save(any(Cart.class))).thenReturn(cart);

        CartDto cartDTO = this.orderService.save(CartBuilder.getOrderFormDto());

        assertThat(cartDTO.getDeliveryDate()).isEqualTo(cart.getDeliveryDate());
        assertThat(cartDTO.getQuantity()).isEqualTo(cart.getQuantity());
        assertThat(cartDTO.getPurchaseDate()).isEqualTo(cart.getPurchaseDate());
        assertThat(cartDTO.getIdUser()).isEqualTo(cart.getUser().getId());
    }

    @Test
    @DisplayName("List all orders")
    public void listOrders() {
        Cart cart = CartBuilder.getOrder();

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Cart> carts = Arrays.asList(cart);
        Page<Cart> page = new PageImpl<>(carts, pageRequest, 1);

        when(this.repository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<CartDto> pageOrderDTO = this.orderService.findAll(pageRequest);

        assertThat(pageOrderDTO.getContent()).hasSize(1);
        assertThat(pageOrderDTO.getTotalPages()).isEqualTo(1);
        assertThat(pageOrderDTO.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("FindById orders")
    public void findByIdOrder() {
        Cart cart = CartBuilder.getOrder();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(cart));

        CartDto cartDTO = this.orderService.findById(cart.getId());

        assertThat(cartDTO.getId()).isNotNull();
        assertThat(cartDTO.getDeliveryDate()).isEqualTo(cart.getDeliveryDate());
        assertThat(cartDTO.getPurchaseDate()).isEqualTo(cart.getPurchaseDate());
        assertThat(cartDTO.getQuantity()).isEqualTo(cart.getQuantity());

    }

    @Test
    @DisplayName("findById Order not found")
    public void findByIdOrder_NotFound() {
        Cart cart = CartBuilder.getOrder();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.orderService.findById(cart.getId()));
    }


    @Test
    @DisplayName("Update order")
    public void updateOrder() {
        Cart cart = CartBuilder.getOrder();
        CartFormDto cartFormDTO = CartBuilder.getOrderFormDto();
        cartFormDTO.setQuantity(2);

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(cart));
        when(this.repository.save(any(Cart.class))).thenReturn(cart);

        CartDto cartDTO = this.orderService.update(cart.getId(), cartFormDTO);

        assertThat(cartDTO.getId()).isNotNull();
        assertThat(cartDTO.getDeliveryDate()).isEqualTo(cartFormDTO.getDeliveryDate());
        assertThat(cartDTO.getPurchaseDate()).isEqualTo(cartFormDTO.getPurchaseDate());
        assertThat(cartDTO.getQuantity()).isEqualTo(cartFormDTO.getQuantity());

    }

    @Test
    @DisplayName("Update order not found")
    public void updateOrder_NotFound() {
        Cart cart = CartBuilder.getOrder();

        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.orderService.update(cart.getId(), CartBuilder.getOrderFormDto()));
    }

    @Test
    @DisplayName("Delete order")
    public void deleteOrder() {
        Cart cart = CartBuilder.getOrder();

        when(this.repository.findById(anyLong())).thenReturn(Optional.of(cart));

        this.orderService.deleteById(1L);

        verify(this.repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete order not found")
    public void deleteOrder_NotFound() {
        when(this.repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> this.orderService.deleteById(1L));
    }
}
