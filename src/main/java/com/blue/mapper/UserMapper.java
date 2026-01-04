package com.blue.mapper;


import com.blue.dto.UserDTO;
import com.blue.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Integration with Spring DI
public interface UserMapper {
    UserDTO toDto(UserEntity entity);
    UserEntity toEntity(UserDTO dto);
}

