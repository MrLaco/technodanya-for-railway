package ru.kpfu.itis.technodanyaspring.service.impl;

import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import ru.kpfu.itis.technodanyaspring.model.User;
import ru.kpfu.itis.technodanyaspring.repository.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.text.*;
import java.util.*;
import java.util.stream.*;

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
    public Optional<CreateArticleResponseDto> getArticleById(Integer id) {
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

    @Override
    public CreateArticleResponseDto update(Integer id, CreateArticleRequestDto createArticleRequestDto) throws ArticleNotFoundException {
        Article entityToSave = articleRepository.findArticleById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Статья с id " + id + " не найдена!"));

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

        List<Image> images = Arrays.stream(createArticleRequestDto.getImages().split(","))
                .map(imageString -> Image
                        .builder()
                        .url(imageString)
                        .article(entityToSave)
                        .build())
                .toList();

        entityToSave.setTitle(createArticleRequestDto.getTitle());
        entityToSave.setContent(createArticleRequestDto.getContent());
        entityToSave.setDate(formatForDateNow.format(dateNow));
        entityToSave.setDescription(createArticleRequestDto.getDescription());
        entityToSave.setImages(images);

        articleRepository.save(entityToSave);

        return ArticleMapper.INSTANCE.articleToCreateArticleResponseDto(entityToSave);
    }

    @Override
    public List<CreateArticleResponseDto> getAllArticlesByUser(User user) {
        return articleRepository.getAllByUser(user)
                .stream()
                .map(ArticleMapper.INSTANCE::articleToCreateArticleResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreateArticleResponseDto> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleMapper.INSTANCE::articleToCreateArticleResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreateArticleResponseDto> getAllByTitle(String title) {
        return articleRepository.getAllByTitle(title)
                .stream()
                .map(ArticleMapper.INSTANCE::articleToCreateArticleResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(Integer articleId) {
        articleRepository.deleteById(articleId);
    }
}
