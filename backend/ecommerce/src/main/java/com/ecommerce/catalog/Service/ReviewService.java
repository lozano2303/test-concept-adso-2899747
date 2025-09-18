package com.ecommerce.catalog.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.catalog.Entity.Review;
import com.ecommerce.catalog.IRepository.IReviewRepository;
import com.ecommerce.catalog.IService.IReviewService;
import com.ecommerce.common.Service.ABaseService;

/**
 * Implementación del servicio para la entidad Review.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class ReviewService extends ABaseService<Review, Long> implements IReviewService {

    private final IReviewRepository reviewRepository;

    public ReviewService(IReviewRepository reviewRepository) {
        super(reviewRepository);
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}