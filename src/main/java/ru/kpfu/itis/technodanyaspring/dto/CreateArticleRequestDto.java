package ru.kpfu.itis.technodanyaspring.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
public class CreateArticleRequestDto {

    @NotBlank(message = "Title shouldn't be blank")
    private String title;

    @NotBlank(message = "Description shouldn't be blank")
    private String description;

    @NotBlank(message = "Content shouldn't be blank")
    private String content;

    private String images;
}
