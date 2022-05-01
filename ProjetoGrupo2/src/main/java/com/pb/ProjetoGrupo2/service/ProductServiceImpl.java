package com.pb.ProjetoGrupo2.service;

import com.pb.ProjetoGrupo2.config.validation.ObjectNotFoundException;
import com.pb.ProjetoGrupo2.constants.Type;
import com.pb.ProjetoGrupo2.dto.ProductDTO;
import com.pb.ProjetoGrupo2.dto.ProductFormDTO;
import com.pb.ProjetoGrupo2.entities.Product;
import com.pb.ProjetoGrupo2.repository.OrderRepository;
import com.pb.ProjetoGrupo2.repository.ProductRepository;
import com.pb.ProjetoGrupo2.repository.UserRepository;
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
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDTO postProduct(ProductFormDTO productFormDTO) {
        Product product = productRepository.save(modelMapper.map(productFormDTO, Product.class));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public Page<ProductDTO> getAllProducts(String type, Pageable pageable) {

        Page<Product> products;
        if(type == null){
            products = productRepository.findAll(pageable);
        }else {
            products = productRepository.findByType(Type.valueOf(type),pageable);
        }

        List<ProductDTO> productDTOList =
                products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
        return new PageImpl<>(productDTOList, pageable, productDTOList.size());
    }

    @Override
    public ProductDTO getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return modelMapper.map(product.get(), ProductDTO.class);
        }
        throw new ObjectNotFoundException("Product not found!");
    }

    @Override
    public ProductDTO putProduct(Long id, ProductFormDTO productFormDto) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            Product productUpdated = modelMapper.map(productFormDto, Product.class);
            productUpdated.setId(id);
            productRepository.save(productUpdated);
            return modelMapper.map(productUpdated, ProductDTO.class);
        }
        throw new ObjectNotFoundException("Product not found!");
    }

    public String deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){

            productRepository.deleteById(id);
            String idProduct = product.get().getId().toString();
            return "Product " + idProduct + " deleted with success!";
        }
        throw new ObjectNotFoundException("Product not found!");
    }
}
