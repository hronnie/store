package com.roche.store.mapper;

import com.roche.store.core.domain.Product;
import com.roche.store.core.web.transfer.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product entity);

    Product toEntity(ProductDto dto);

    List<ProductDto> toProductDTOs(List<Product> products);

}
