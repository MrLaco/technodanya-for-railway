package ru.kpfu.itis.technodanyaspring.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> getReviewsByUser(User user);
    Optional<Review> getReviewById(Integer reviewId);
}
