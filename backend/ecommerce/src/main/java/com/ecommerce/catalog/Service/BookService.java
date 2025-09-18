package com.ecommerce.catalog.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.catalog.IRepository.IBookRepository;
import com.ecommerce.catalog.IService.IBookService;
import com.ecommerce.common.Service.ABaseService;

/**
 * Implementación del servicio para la entidad Book.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class BookService extends ABaseService<Book, Long> implements IBookService {

    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        super(bookRepository);
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByCategoryId(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
}