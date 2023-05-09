package ru.kpfu.itis.technodanyaspring.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Firstname shouldn't be blank")
    private String firstName;

    @NotBlank(message = "Lastname shouldn't be blank")
    private String lastName;

    @NotBlank(message = "Email shouldn't be blank")
    @Pattern(regexp = "^([\\w\\-_]+)@([\\w\\-_]+)\\.([a-z]{2,5})(\\.[a-z]{2,5})?$", message = "Invalid email address")
    private String email;

    @NotBlank(message = "City shouldn't be blank")
    private String city;

    @NotBlank(message = "Password shouldn't be blank")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one letter and one number")
    private String password;
}