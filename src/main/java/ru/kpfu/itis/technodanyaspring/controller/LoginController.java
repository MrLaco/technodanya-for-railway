package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;

import javax.servlet.*;
import javax.servlet.http.*;

@Controller
@AllArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new CreateUserRequestDto());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/profile";
        }

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }
}
