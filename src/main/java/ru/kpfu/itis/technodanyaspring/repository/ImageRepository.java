package ru.kpfu.itis.technodanyaspring.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> getImagesByArticle(Article article);
    Optional<Image> getImageByUrl(String url);
}
