package ru.kpfu.itis.technodanyaspring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;

@Controller
public class MessageController {

    @GetMapping("/chat")
    public String getChat() {
        return "chat";
    }

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public MessageDto message(MessageDto messageDto) {
        return new MessageDto(String.format("Hello, %s", messageDto.getName()), "server");
    }
}