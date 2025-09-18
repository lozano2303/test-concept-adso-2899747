package com.ecommerce.catalog.IService;

import com.ecommerce.catalog.Entity.Category;
import com.ecommerce.common.IService.IBaseService;

/**
 * Interfaz de servicio para la entidad Category.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface ICategoryService extends IBaseService<Category, Long> {
}