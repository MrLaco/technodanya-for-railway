package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.service.*;

@Controller
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public String showArticlesPage(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "logged/articles";
    }

    @PostMapping("/articles")
    public String searchArticles(
            @RequestParam(required = false, name = "search") String articleTitle,
            Model model
    ) {
        model.addAttribute("articleTitle", articleTitle);
        if (articleTitle == null) {
            model.addAttribute("articles", articleService.getAllArticles());
        } else {
            model.addAttribute("articles", articleService.getAllByTitle(articleTitle));
        }
        return "logged/articles";
    }
}
