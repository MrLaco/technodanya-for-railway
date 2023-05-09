package ru.kpfu.itis.technodanyaspring.service.impl;

import lombok.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.model.*;
import ru.kpfu.itis.technodanyaspring.repository.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDto> findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .stream()
                .map(UserMapper.INSTANCE::userToUserResponseDto)
                .findFirst();
    }

    @Override
    public Optional<UserResponseDto> findById(Integer id) {
        return userRepository.findUserById(id)
                .stream()
                .map(UserMapper.INSTANCE::userToUserResponseDto)
                .findFirst();
    }

    @Override
    public UserResponseDto create(CreateUserRequestDto createUserRequestDto) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        User user = User.builder()
                .firstName(createUserRequestDto.getFirstName())
                .lastName(createUserRequestDto.getLastName())
                .city(createUserRequestDto.getCity())
                .email(createUserRequestDto.getEmail())
                .password(encoder.encode(createUserRequestDto.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return UserMapper.INSTANCE.userToUserResponseDto(user);
    }
}
