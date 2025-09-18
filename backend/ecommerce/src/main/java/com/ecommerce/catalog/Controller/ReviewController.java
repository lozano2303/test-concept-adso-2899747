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

import com.ecommerce.catalog.Dto.ReviewDto;
import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.catalog.Entity.Review;
import com.ecommerce.catalog.IService.IBookService;
import com.ecommerce.catalog.IService.IReviewService;
import com.ecommerce.user.Entity.User;
import com.ecommerce.user.IService.IUserService;

/**
 * REST Controller for the Review entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final IReviewService reviewService;
    private final IUserService userService;
    private final IBookService bookService;

    public ReviewController(IReviewService reviewService, IUserService userService, IBookService bookService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<Review> reviews = reviewService.findAll();
        List<ReviewDto> reviewDtos = reviews.stream().map(review -> convertToDto(review)).collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.findById(id);
        if (review.isPresent()) {
            return ResponseEntity.ok(convertToDto(review.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByBookId(@PathVariable Long bookId) {
        List<Review> reviews = reviewService.findByBookId(bookId);
        List<ReviewDto> reviewDtos = reviews.stream().map(review -> convertToDto(review)).collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<Review> reviews = reviewService.findByUserId(userId);
        List<ReviewDto> reviewDtos = reviews.stream().map(review -> convertToDto(review)).collect(Collectors.toList());
        return ResponseEntity.ok(reviewDtos);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        Review review = convertToEntity(reviewDto);
        Review savedReview = reviewService.save(review);
        return ResponseEntity.ok(convertToDto(savedReview));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        Optional<Review> existingReview = reviewService.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setContenido(reviewDto.getContenido());
            review.setRating(reviewDto.getRating());
            // Update user association if userId is provided
            if (reviewDto.getUserId() != null) {
                Optional<User> user = userService.findById(reviewDto.getUserId());
                if (user.isPresent()) {
                    review.setUser(user.get());
                } else {
                    // Handle case where user does not exist
                    review.setUser(null);
                }
            } else {
                // If userId is null, remove association
                review.setUser(null);
            }
            // Update book association if bookId is provided
            if (reviewDto.getBookId() != null) {
                Optional<Book> book = bookService.findById(reviewDto.getBookId());
                if (book.isPresent()) {
                    review.setBook(book.get());
                } else {
                    // Handle case where book does not exist
                    review.setBook(null);
                }
            } else {
                // If bookId is null, remove association
                review.setBook(null);
            }
            Review updatedReview = reviewService.save(review);
            return ResponseEntity.ok(convertToDto(updatedReview));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        if (reviewService.findById(id).isPresent()) {
            reviewService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ReviewDto convertToDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setContenido(review.getContenido());
        dto.setRating(review.getRating());
        // User can be null - reviews can exist without being associated to a user
        if (review.getUser() != null) {
            dto.setUserId(review.getUser().getId());
        }
        // Book can be null - reviews can exist without being associated to a book
        if (review.getBook() != null) {
            dto.setBookId(review.getBook().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private Review convertToEntity(ReviewDto dto) {
        Review review = new Review();
        review.setContenido(dto.getContenido());
        review.setRating(dto.getRating());
        // If userId is provided, fetch and set the user
        if (dto.getUserId() != null) {
            Optional<User> user = userService.findById(dto.getUserId());
            if (user.isPresent()) {
                review.setUser(user.get());
            } else {
                // Handle case where user does not exist - could throw exception or log warning
                // For now, we'll allow the review to be created without user association
            }
        }
        // If bookId is provided, fetch and set the book
        if (dto.getBookId() != null) {
            Optional<Book> book = bookService.findById(dto.getBookId());
            if (book.isPresent()) {
                review.setBook(book.get());
            } else {
                // Handle case where book does not exist - could throw exception or log warning
                // For now, we'll allow the review to be created without book association
            }
        }
        return review;
    }
}