package ru.kpfu.itis.technodanyaspring.service;

import ru.kpfu.itis.technodanyaspring.dto.*;

import java.util.*;

public interface UserService {

    List<UserResponseDto> findAll();
    Optional<UserResponseDto> findByEmail(String email);
    Optional<UserResponseDto> findById(Integer id);
    UserResponseDto create(CreateUserRequestDto createUserRequestDto);
}
