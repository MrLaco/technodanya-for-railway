package ru.kpfu.itis.technodanyaspring.service;

import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface ArticleService {

    Optional<CreateArticleResponseDto> findArticleByDateAndUser(String date, User user);
    Optional<CreateArticleResponseDto> getArticleById(Integer id);
    CreateArticleResponseDto create(CreateArticleRequestDto createArticleRequestDto);
    CreateArticleResponseDto update(Integer id, CreateArticleRequestDto createArticleRequestDto) throws ArticleNotFoundException;
    List<CreateArticleResponseDto> getAllArticlesByUser(User user);
    List<CreateArticleResponseDto> getAllArticles();
    List<CreateArticleResponseDto> getAllByTitle(String title);
    void deleteArticle(Integer articleId);
}
