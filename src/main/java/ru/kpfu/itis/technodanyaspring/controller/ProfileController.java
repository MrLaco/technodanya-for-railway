package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.service.*;
import ru.kpfu.itis.technodanyaspring.util.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final PremiumService premiumService;

    @GetMapping("/profile")
    public String profile(Model model) throws UserNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserResponseDto userResponseDto = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email " + email + " не найден"));
        Optional<PremiumDto> premiumDto = premiumService.findPremiumByUser(UserMapper.INSTANCE.userResponseDtoToUser(userResponseDto));

        String premiumStatus;
        premiumStatus = premiumDto.filter(PremiumDto::isActive).map(dto -> "Активна =)").orElse("Неактивна :(");

        model.addAttribute(userResponseDto);
        model.addAttribute("td_premium", premiumStatus);

        String iphone14proCost = "от " + Math.round(DollarRateFetcher.fetchDollarRate() * 999) + " рублей";
        model.addAttribute("iphone14proCost", iphone14proCost);

        String macbookPro13Cost = "от " + Math.round(DollarRateFetcher.fetchDollarRate() * 1299) + " рублей";
        model.addAttribute("macbookPro13Cost", macbookPro13Cost);

        return "logged/profile";
    }
}
