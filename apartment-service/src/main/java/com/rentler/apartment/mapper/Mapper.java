package com.rentler.apartment.mapper;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}

