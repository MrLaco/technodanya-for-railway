package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class MyArticlesController {

    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping("/my_articles")
    public String myArticles(Model model) throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserResponseDto userResponseDto = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email " + email + " не найден"));

        List<CreateArticleResponseDto> articles = articleService.getAllArticlesByUser(UserMapper.INSTANCE.userResponseDtoToUser(userResponseDto));

        model.addAttribute("articles", articles);

        return "logged/my_articles";
    }

    // CRUD: DELETE
    @PostMapping("/my_articles/{id}/delete")
    public String deleteArticle(@PathVariable("id") Integer articleId) {
        articleService.deleteArticle(articleId);
        return "redirect:/my_articles";
    }
}
