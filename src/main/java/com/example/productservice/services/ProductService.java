package com.example.productservice.services;

import com.example.productservice.exceptions.ProductEntityNotFoundException;
import com.example.productservice.models.ProductEntity;
import com.example.productservice.models.dtos.ProductDto;
import com.example.productservice.models.dtos.mapper.ProductDtoToProductMapper;
import com.example.productservice.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoToProductMapper productDtoToProductMapper;

    public ProductEntity createProduct(ProductDto productDto) {
        ProductEntity productEntity = productDtoToProductMapper.mapProductDtoToProduct(productDto);
        productRepository.save(productEntity);
        return productEntity;
    }

    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(ProductEntityNotFoundException::new);
    }

    public ProductEntity updateProduct(Long id, ProductEntity productEntity) {
        if (!productRepository.existsById(id)) {
            throw new ProductEntityNotFoundException();
        }
        productEntity.setProductId(id);
        return productRepository.save(productEntity);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductEntityNotFoundException();
        }
        productRepository.deleteById(id);
    }

    public Page<ProductEntity> listProducts(Integer page, Integer pageLimit) {
        return productRepository.findAll(PageRequest.of(page, pageLimit));
    }
}
