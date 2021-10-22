package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.entities.Review;

public interface ReviewService {
    void createReview(Long partnerId, Review review);
}
