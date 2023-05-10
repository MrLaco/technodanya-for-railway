package ru.kpfu.itis.technodanyaspring.dto;

import lombok.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import java.util.*;

@Data
public class CreateArticleResponseDto {

    Integer id;

    private String title;

    private String description;

    private String content;

    private List<Image> images;

    private String date;

    private User user;
}
