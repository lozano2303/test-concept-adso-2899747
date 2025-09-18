package com.ecommerce.common.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interfaz base para repositorios.
 * Se marca con @NoRepositoryBean porque no debe instanciarse directamente.
 */
@NoRepositoryBean
public interface IBaseRepository<T, ID> extends JpaRepository<T, ID> {
    
}