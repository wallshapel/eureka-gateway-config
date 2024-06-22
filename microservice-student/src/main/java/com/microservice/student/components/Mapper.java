package com.microservice.student.components;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public <D, T> D toDto(T entity, Class<D> dtoClass) { // <D, T> D Significa que recibe un DTO cualquiera y una Entidad cualquiera y devuelve un DTO
        return modelMapper.map(entity, dtoClass);
    }

    public <D, T> T toEntity(D dto, Class<T> entityClass) { // <D, T> T Significa que recibe un DTO cualquiera y una Entidad cualquiera y devuelve una Entidad
        return modelMapper.map(dto, entityClass);
    }

    public <D, T> List<D> toDtoList(List<T> entityList, Class<D> dtoClass) {
        return entityList.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

}