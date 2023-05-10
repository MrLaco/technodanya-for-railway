package ru.kpfu.itis.technodanyaspring.service;

import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface ArticleService {

    Optional<CreateArticleResponseDto> findArticleByDateAndUser(String date, User user);
    Optional<CreateArticleResponseDto> findArticleById(Integer id);
    CreateArticleResponseDto create(CreateArticleRequestDto createArticleRequestDto);
}
