package ru.kpfu.itis.technodanyaspring.dto;

import lombok.*;

@Data
public class UserResponseDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String city;
}
