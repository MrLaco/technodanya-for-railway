package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.service.*;

@Controller
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public String getReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());

        return "logged/reviews";
    }
}
