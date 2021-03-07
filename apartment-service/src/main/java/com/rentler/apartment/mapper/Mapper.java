package com.rentler.apartment.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class Mapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    @Autowired
    ModelMapper mapper;

    public Mapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapFieldsFromEntity(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapFieldsFromDto(source, destination);
            return context.getDestination();
        };
    }

    void mapFieldsFromEntity(E source, D destination) {
    }

    void mapFieldsFromDto(D source, E destination) {
    }

}


