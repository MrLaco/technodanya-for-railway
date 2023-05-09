package ru.kpfu.itis.technodanyaspring.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class PremiumController {

    @GetMapping("/about_premium")
    public String aboutPremium() {
        return "logged/about_premium";
    }
}
