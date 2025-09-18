package com.ecommerce.catalog.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.catalog.Dto.BookDto;
import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.catalog.Entity.Category;
import com.ecommerce.catalog.IService.IBookService;
import com.ecommerce.catalog.IService.ICategoryService;

/**
 * REST Controller for the Book entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final IBookService bookService;
    private final ICategoryService categoryService;

    public BookController(IBookService bookService, ICategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> books = bookService.findAll();
        List<BookDto> bookDtos = books.stream().map(book -> convertToDto(book)).collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(convertToDto(book.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BookDto>> getBooksByCategoryId(@PathVariable Long categoryId) {
        List<Book> books = bookService.findByCategoryId(categoryId);
        List<BookDto> bookDtos = books.stream().map(book -> convertToDto(book)).collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookService.save(book);
        return ResponseEntity.ok(convertToDto(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Optional<Book> existingBook = bookService.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitulo(bookDto.getTitulo());
            book.setAutor(bookDto.getAutor());
            book.setPrecio(bookDto.getPrecio());
            book.setStock(bookDto.getStock());
            // Update category association if categoryId is provided
            if (bookDto.getCategoryId() != null) {
                Optional<Category> category = categoryService.findById(bookDto.getCategoryId());
                if (category.isPresent()) {
                    book.setCategory(category.get());
                } else {
                    // Handle case where category does not exist
                    book.setCategory(null);
                }
            } else {
                // If categoryId is null, remove association
                book.setCategory(null);
            }
            Book updatedBook = bookService.save(book);
            return ResponseEntity.ok(convertToDto(updatedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setTitulo(book.getTitulo());
        dto.setAutor(book.getAutor());
        dto.setPrecio(book.getPrecio());
        dto.setStock(book.getStock());
        // Category can be null - books can exist without being associated to a category
        if (book.getCategory() != null) {
            dto.setCategoryId(book.getCategory().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private Book convertToEntity(BookDto dto) {
        Book book = new Book();
        book.setTitulo(dto.getTitulo());
        book.setAutor(dto.getAutor());
        book.setPrecio(dto.getPrecio());
        book.setStock(dto.getStock());
        // If categoryId is provided, fetch and set the category
        if (dto.getCategoryId() != null) {
            Optional<Category> category = categoryService.findById(dto.getCategoryId());
            if (category.isPresent()) {
                book.setCategory(category.get());
            } else {
                // Handle case where category does not exist - could throw exception or log warning
                // For now, we'll allow the book to be created without category association
            }
        }
        return book;
    }
}