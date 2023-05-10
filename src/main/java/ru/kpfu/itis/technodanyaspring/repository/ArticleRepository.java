package ru.kpfu.itis.technodanyaspring.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query(value = "select * from articles where articles.id = :id", nativeQuery = true)
    Optional<Article> findArticleById(Integer id);

    Optional<Article> findArticleByDateAndUser(String date, User user);

    List<Article> getAllByTitle(String title);
    List<Article> getAllByDateAndUser(String date, User user);
    List<Article> getAllByUser(User user);
}
