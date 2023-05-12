package ru.kpfu.itis.technodanyaspring.dto;

import lombok.*;
import ru.kpfu.itis.technodanyaspring.model.*;

@Data
public class ReviewResponseDto {

    private String date;

    private String content;

    private User user;

    private Article article;
}
