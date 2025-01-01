package com.example.productservice.models.dtos.mapper;

import com.example.productservice.models.ProductEntity;
import com.example.productservice.models.dtos.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoToProductMapper {
    ProductEntity mapProductDtoToProduct(ProductDto productDto);
}
