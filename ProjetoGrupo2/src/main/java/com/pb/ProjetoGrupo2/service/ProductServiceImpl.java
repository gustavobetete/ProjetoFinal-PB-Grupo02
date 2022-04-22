package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.dto.ProductDto;
import com.pb.ProjetoGrupo2.dto.ProductFormDto;
import com.pb.ProjetoGrupo2.dto.ProductOrderFormDto;
import com.pb.ProjetoGrupo2.entities.Order;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
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
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
<<<<<<< HEAD
=======
    private OrderRepository orderRepository;

    @Autowired
>>>>>>> e291120593c8544b18e2f5559920660ccbe2383b
    private ModelMapper modelMapper;

    @Override
    public Page<ProductDto> findAll(Pageable page){
        Page<Product> products = this.productRepository.findAll(page);
        List<ProductDto> listProducts = products.stream().map(product ->
                modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return new PageImpl<ProductDto>(listProducts, page, products.getTotalElements());
    }

    @Override
    public ProductDto findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return modelMapper.map(product.get(), ProductDto.class);
        }
        throw new ObjectNotFoundException("Product not found!");
    }

    @Override
    public ProductDto save(ProductFormDto productFormDto){
        Product product = this.productRepository.save(modelMapper.map(productFormDto, Product.class));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto update(Long id, ProductFormDto productFormDto) {
        Optional<Product> product = this.productRepository.findById(id);
        if(product.isPresent()) {
            Product productUpdated = modelMapper.map(productFormDto, Product.class);
            productUpdated.setId(id);
            productRepository.save(productUpdated);
            return modelMapper.map(productUpdated, ProductDto.class);
        }
        throw new ObjectNotFoundException("Product not found!");
    }

    @Override
    public Object deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Product not found!");
    }

    @Override
    public ResponseEntity createProductOrder(ProductOrderFormDto productOrderFormDto) {

        Optional<Product> product = productRepository.findById(productOrderFormDto.getProductId());
        Optional<Order> order = orderRepository.findById(productOrderFormDto.getOrderId());

        if(product.isPresent() && order.isPresent()){

            order.get().getProducts().add(product.get());
            orderRepository.save(order.get());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> removeProductOrder(Long productId, Long orderId) {

        Optional<Product> product = productRepository.findById(productId);
        Optional<Order> order = orderRepository.findById(orderId);

        if(product.isPresent() && order.isPresent()){
            order.get().getProducts().remove(product.get());
            orderRepository.save(order.get());

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

}
