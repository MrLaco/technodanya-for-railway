package ru.kpfu.itis.technodanyaspring.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.repository.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponseDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper.INSTANCE::reviewToReviewResponseDto)
                .collect(Collectors.toList());
    }
}
