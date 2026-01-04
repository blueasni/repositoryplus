package com.blue.mapper;

import com.blue.dto.ProductDTO;
import com.blue.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(ProductEntity entity);
    ProductEntity toEntity(ProductDTO dto);
}
