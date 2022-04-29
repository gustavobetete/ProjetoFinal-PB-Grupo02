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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

//    private Map<Long, List<Integer>> idUsuarioEQuantidadePedida = new HashMap<>();
//    private List<Integer> idsDosPedidos = new ArrayList<>();

    @Override
    public Page<OrderDto> findAll(Pageable page){
        Page<Order> orders = this.orderRepository.findAll(page);
        List<OrderDto> listOrders = orders.stream().map(order ->
                modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());

        for(int i = 0; i < listOrders.size(); i++){
            OrderDto orderAtual = listOrders.get(i);
//            listOrders.get(i).getProducts().get(i).setQuantity(orderAtual.getProducts().get(i).getQuantity());

//            listOrders.get(i).getProducts().get(i).setQuantity(idUsuarioEQuantidadePedida.get(orderAtual.getIdUser()).get(i));
        }

        return new PageImpl<OrderDto>(listOrders, page, orders.getTotalElements());

    }

    @Override
    public OrderDto findById(Long id){
        Optional<Order> orders = orderRepository.findById(id);
        if (orders.isPresent()){
            OrderDto order = modelMapper.map(orders.get(), OrderDto.class);
//            for(int i = 0; i < order.getProducts().size(); i++){
//                order.getProducts().get(i).setQuantityInStock(idUsuarioEQuantidadePedida.get(order.getIdUser()).get(i));
//            }
            return order;
        }
        throw new ObjectNotFoundException("Order not found!");
    }

    @Override
    public OrderDto save(OrderFormDto orderFormDto){
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


    private Order createOrder(OrderFormDto orderFormDto, Order order) {
        Double TotalValue = (double) 0;
        //OrderDto orderDto = new OrderDto();


        for(int i = 0; i < order.getProducts().size(); i++ ){
            Optional<Product> optionalProduct = this.productRepository.findById(orderFormDto.getProducts().get(i).getProductId());

            if(optionalProduct.isPresent()){
                //Recupera a quantidade em estoque
                Product product = optionalProduct.get();
                if(product.getQuantityInStock() < orderFormDto.getProducts().get(i).getQuantity())
                //order.getProducts().get(i).setQuantityInStock(product.get().getQuantityInStock());
                order.getProducts().get(i).setName(product.getName());
                order.getProducts().get(i).setUnitPrice(product.getUnitPrice());
                order.getProducts().get(i).setType(product.getType());
                order.getProducts().get(i).setQuantityInStock(orderFormDto.getProducts().get(i).getQuantity());

                product.setQuantityInStock(product.getQuantityInStock() - orderFormDto.getProducts().get(i).getQuantity());
                this.productRepository.save(product);
                //order.setQuantity(orderFormDto.getProducts().get(i).getQuantity());
                //orderDto.getProducts().get(i).setQuantity().;
                TotalValue += order.getProducts().get(i).getUnitPrice() * order.getProducts().get(i).getQuantityInStock();
//                Integer inStock = product.getQuantityInStock() - orderFormDto.getProducts().get(i).getQuantity();

//                if(inStock >= 0){
//                    //Atualiza o valor depois de descontar do pedido
//                    product.get().setQuantityInStock(inStock);
//
////                    idsDosPedidos.add(order.getProducts().get(i).getQuantityInStock());
////                    idUsuarioEQuantidadePedida.put(orderFormDto.getIdUser(), idsDosPedidos);
//
//                    // productRepository.save(product.get());
//                }else {
//                    String productNotFound = product.get().getName();
//                    String.format("Product %s not found!", productNotFound);

                }
            throw new RuntimeException("Produto fora de estoque!");
            }
        order.setTotal(TotalValue);
        return order;
    }
}
