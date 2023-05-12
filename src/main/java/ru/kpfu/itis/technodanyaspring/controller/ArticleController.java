package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article")
    public String article(@RequestParam Optional<Integer> id, Model model) throws ArticleNotFoundException, IncorrectArticleIdParamException {

        Integer articleID = id.orElseThrow(() -> new IncorrectArticleIdParamException("ID статьи в запросе пустой!"));

        CreateArticleResponseDto createArticleResponseDto = articleService.getArticleById(articleID).orElseThrow(() -> new ArticleNotFoundException("Статья с id " + id + "не найдена!"));
        model.addAttribute(createArticleResponseDto);

        return "logged/article";
    }
}
