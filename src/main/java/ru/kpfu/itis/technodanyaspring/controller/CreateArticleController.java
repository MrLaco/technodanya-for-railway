package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.service.*;

@Controller
@RequiredArgsConstructor
public class CreateArticleController {

    private final ArticleService articleService;

    @GetMapping("/create_article")
    public String createArticle(Model model) {
        model.addAttribute("article", new CreateArticleRequestDto());
        return "logged/create_article";
    }

    @PostMapping("/create_article")
    public String createArticle(@ModelAttribute("article") CreateArticleRequestDto article) {
        CreateArticleResponseDto articleResponseDto = articleService.create(article);

        return "redirect:/article?id=" + articleResponseDto.getId();
    }
}
