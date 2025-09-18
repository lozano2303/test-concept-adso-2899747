package com.ecommerce.catalog.IRepository;

import java.util.List;

import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.common.IRepository.IBaseRepository;

/**
 * Interfaz de repositorio para la entidad Book.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IBookRepository extends IBaseRepository<Book, Long> {

    /**
     * Busca todos los libros de una categoría específica.
     * @param categoryId El ID de la categoría.
     * @return Una lista de libros de la categoría.
     */
    List<Book> findByCategoryId(Long categoryId);
}