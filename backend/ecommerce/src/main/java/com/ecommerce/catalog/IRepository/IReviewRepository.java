package com.ecommerce.catalog.IRepository;

import java.util.List;

import com.ecommerce.catalog.Entity.Review;
import com.ecommerce.common.IRepository.IBaseRepository;

/**
 * Interfaz de repositorio para la entidad Review.
 * Extiende IBaseRepository para operaciones CRUD básicas.
 */
public interface IReviewRepository extends IBaseRepository<Review, Long> {

    /**
     * Busca todas las reseñas de un libro específico.
     * @param bookId El ID del libro.
     * @return Una lista de reseñas del libro.
     */
    List<Review> findByBookId(Long bookId);

    /**
     * Busca todas las reseñas de un usuario específico.
     * @param userId El ID del usuario.
     * @return Una lista de reseñas del usuario.
     */
    List<Review> findByUserId(Long userId);
}