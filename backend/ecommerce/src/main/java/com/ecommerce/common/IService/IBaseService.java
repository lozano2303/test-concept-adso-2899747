package com.ecommerce.common.IService;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz base para servicios.
 */
public interface IBaseService<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    void deleteById(ID id);
}