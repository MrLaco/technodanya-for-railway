package ru.kpfu.itis.technodanyaspring.dto.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.model.*;

@Mapper
public interface PremiumMapper {

    PremiumMapper INSTANCE = Mappers.getMapper(PremiumMapper.class);

    PremiumDto premiumToUserPremiumDto(Premium user);
}
