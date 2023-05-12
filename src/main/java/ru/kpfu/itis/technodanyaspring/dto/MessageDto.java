package ru.kpfu.itis.technodanyaspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    private String message;
    private String name;
}