package ru.kpfu.itis.technodanyaspring.dto.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.model.*;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    CreateArticleResponseDto articleToCreateArticleResponseDto(Article article);
}
