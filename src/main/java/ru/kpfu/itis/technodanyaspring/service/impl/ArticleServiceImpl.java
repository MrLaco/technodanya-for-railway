package ru.kpfu.itis.technodanyaspring.service.impl;

import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import ru.kpfu.itis.technodanyaspring.model.User;
import ru.kpfu.itis.technodanyaspring.repository.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.text.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<CreateArticleResponseDto> findArticleByDateAndUser(String date, User user) {
        return articleRepository.findArticleByDateAndUser(date, user)
                .stream()
                .map(ArticleMapper.INSTANCE::articleToCreateArticleResponseDto)
                .findFirst();
    }

    @Override
    public Optional<CreateArticleResponseDto> findArticleById(Integer id) {
        return articleRepository.findArticleById(id)
                .stream()
                .map(ArticleMapper.INSTANCE::articleToCreateArticleResponseDto)
                .findFirst();
    }

    @Override
    public CreateArticleResponseDto create(CreateArticleRequestDto createArticleRequestDto) {

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с " + email + " не найден!"));

        Article article = Article.builder()
                .title(createArticleRequestDto.getTitle())
                .content(createArticleRequestDto.getContent())
                .description(createArticleRequestDto.getDescription())
                .date(formatForDateNow.format(dateNow))
                .user(user)
                .build();

        List<Image> images = Arrays
                .stream(createArticleRequestDto.getImages().split(","))
                .map(imageString -> Image
                        .builder()
                        .article(article)
                        .url(imageString)
                        .build())
                .toList();

        article.setImages(images);

        articleRepository.save(article);

        return ArticleMapper.INSTANCE.articleToCreateArticleResponseDto(article);
    }
}
