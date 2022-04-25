package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.dto.CartDto;
import com.pb.ProjetoGrupo2.dto.CartFormDto;
import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.entities.Cart;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.CartRepository;
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
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CartDto> findAll(Pageable page){
        Page<Cart> orders = this.cartRepository.findAll(page);
        List<CartDto> listOrders = orders.stream().map(order ->
                modelMapper.map(order, CartDto.class)).collect(Collectors.toList());
        return new PageImpl<CartDto>(listOrders, page, orders.getTotalElements());
    }

    @Override
    public CartDto findById(Long id){
        Optional<Cart> orders = cartRepository.findById(id);
        if (orders.isPresent()){
            return modelMapper.map(orders.get(), CartDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public CartDto save(CartFormDto cartFormDto){
        Cart cart = modelMapper.map(cartFormDto, Cart.class);
        cart.setId(null);
        this.cartRepository.save(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto update(Long id, CartFormDto cartFormDto) {
        Optional<Cart> order = this.cartRepository.findById(id);
        if(order.isPresent()) {
            Cart cartUpdated = modelMapper.map(cartFormDto, Cart.class);
            cartUpdated.setId(id);
            cartRepository.save(cartUpdated);
            return modelMapper.map(cartUpdated, CartDto.class);
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<Cart> order = cartRepository.findById(id);
        if(order.isPresent()){
            cartRepository.deleteById(id);
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
