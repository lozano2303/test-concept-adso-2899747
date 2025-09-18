package com.ecommerce.catalog.Service;

import org.springframework.stereotype.Service;

import com.ecommerce.catalog.Entity.Category;
import com.ecommerce.catalog.IRepository.ICategoryRepository;
import com.ecommerce.catalog.IService.ICategoryService;
import com.ecommerce.common.Service.ABaseService;

/**
 * Implementación del servicio para la entidad Category.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class CategoryService extends ABaseService<Category, Long> implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }
}