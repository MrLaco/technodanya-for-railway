package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.service.*;

@Controller
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new CreateUserRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") CreateUserRequestDto user) {
        userService.create(user);

        return "redirect:/login";
    }
}
