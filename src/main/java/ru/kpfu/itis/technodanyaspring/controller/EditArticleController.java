package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import ru.kpfu.itis.technodanyaspring.service.*;

@Controller
@RequiredArgsConstructor
public class EditArticleController {

    private final ArticleService articleService;

    // CRUD: UPDATE
    @GetMapping("/my_articles/{id}/edit")
    public String getArticle(@PathVariable("id") Integer articleId, Model model) throws ArticleNotFoundException {
        CreateArticleResponseDto createArticleResponseDto = articleService.getArticleById(articleId).orElseThrow(() -> new ArticleNotFoundException("Статья с id " + articleId + "не найдена!"));
        model.addAttribute("article", createArticleResponseDto);

        StringBuilder images = new StringBuilder();
        for (Image image : createArticleResponseDto.getImages()) {
            images.append(image.getUrl());
            images.append(",");
        }

        // Тут links содержит просто список ссылок, дебаггер это видит даже
        String links = images.toString();

        model.addAttribute("links", links);

        return "logged/edit_article";
    }

    @PostMapping("/my_articles/{id}/edit")
    public String updateArticle(@PathVariable("id") Integer articleId, @ModelAttribute("article") CreateArticleRequestDto articleDto) throws ArticleNotFoundException {
        CreateArticleResponseDto articleResponseDto = articleService.update(articleId, articleDto);

        return String.format("redirect:/my_articles/%s/edit", articleResponseDto.getId());
    }
}
