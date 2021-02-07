package ru.kirikomp.arch.converter;

public interface ConverterArch<E, D>  {
    D convertToDto(E entity);
    E convertToEntity(D dto);
}
