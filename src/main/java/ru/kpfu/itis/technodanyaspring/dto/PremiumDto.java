package ru.kpfu.itis.technodanyaspring.dto;

import lombok.*;

@Data
public class PremiumDto {

    private Integer id;
    private boolean isActive;
    private String purchaseDate;
}
