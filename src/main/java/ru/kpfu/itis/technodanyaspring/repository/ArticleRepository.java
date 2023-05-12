package ru.kpfu.itis.technodanyaspring.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query(value = "select * from articles where articles.id = :id", nativeQuery = true)
    Optional<Article> findArticleById(Integer id);

    @Query("SELECT a FROM articles a WHERE a.date = :date AND a.user = (SELECT u FROM users u WHERE u = :user)")
    Optional<Article> findArticleByDateAndUser(@Param("date") String date, @Param("user") User user);


    List<Article> getAllByTitle(String title);

    List<Article> getAllByDateAndUser(String date, User user);

    List<Article> getAllByUser(User user);
}
