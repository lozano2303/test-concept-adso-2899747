package com.ecommerce.catalog.IRepository;

import com.ecommerce.catalog.Entity.Category;
import com.ecommerce.common.IRepository.IBaseRepository;

/**
 * Interfaz de repositorio para la entidad Category.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface ICategoryRepository extends IBaseRepository<Category, Long> {
}