package com.ecommerce.catalog.IService;

import java.util.List;

import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.common.IService.IBaseService;

/**
 * Interfaz de servicio para la entidad Book.
 * Extiende IBaseService para operaciones CRUD básicas.
 */
public interface IBookService extends IBaseService<Book, Long> {

    /**
     * Busca todos los libros de una categoría específica.
     * @param categoryId El ID de la categoría.
     * @return Una lista de libros de la categoría.
     */
    List<Book> findByCategoryId(Long categoryId);
}