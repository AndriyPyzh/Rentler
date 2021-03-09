package com.rentler.user.mapper;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}

