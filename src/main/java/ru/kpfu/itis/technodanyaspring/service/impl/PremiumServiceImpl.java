package ru.kpfu.itis.technodanyaspring.service.impl;

import lombok.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import ru.kpfu.itis.technodanyaspring.repository.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PremiumServiceImpl implements PremiumService {

    private final PremiumRepository premiumRepository;
    @Override
    public Optional<PremiumDto> findPremiumByUser(User user) {
        return premiumRepository.findPremiumByUser(user)
                .stream()
                .map(PremiumMapper.INSTANCE::premiumToUserPremiumDto)
                .findFirst();
    }
}
