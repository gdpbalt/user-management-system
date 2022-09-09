package com.example.usermanagementsystem.dto.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D dto);
}
