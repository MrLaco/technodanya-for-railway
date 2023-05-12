package ru.kpfu.itis.technodanyaspring.controller;

import org.springframework.boot.web.servlet.error.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import javax.servlet.*;
import javax.servlet.http.*;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "Страничка не найдена =(");
                return "error";
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMessage", "Какая-то ошибка на сервере =(");
                return "error";
            }
        }
        model.addAttribute("errorMessage", "Технические шоколадки на сайте =(");
        return "error";
    }
}
