package ru.kpfu.itis.technodanyaspring.dto.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.model.*;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewResponseDto reviewToReviewResponseDto(Review review);
}
