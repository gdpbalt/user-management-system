package com.example.usermanagementsystem.dto.mapper;

public interface ResponseDtoMapper<D, T> {
    D toDto(T t);
}
