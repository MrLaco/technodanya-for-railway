package ru.kpfu.itis.technodanyaspring.service.impl;

import lombok.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.dto.mapper.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
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

    @Override
    public boolean delete(Integer id) {
        userRepository.deleteById(id);

        return userRepository.findUserById(id).isEmpty();
    }

    @Override
    public UserResponseDto update(Integer id, CreateUserRequestDto createUserRequestDto) throws UserNotFoundException {
        User entityToSave = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("Статья с id " + id + " не найдена!"));

        if (createUserRequestDto.getFirstName() != null) {
            entityToSave.setFirstName(createUserRequestDto.getFirstName());
        }
        if (createUserRequestDto.getLastName() != null) {
            entityToSave.setFirstName(createUserRequestDto.getLastName());
        }
        if (createUserRequestDto.getEmail() != null) {
            entityToSave.setFirstName(createUserRequestDto.getEmail());
        }
        if (createUserRequestDto.getCity() != null) {
            entityToSave.setFirstName(createUserRequestDto.getCity());
        }
        if (createUserRequestDto.getPassword() != null) {
            entityToSave.setFirstName(createUserRequestDto.getPassword());
        }
        userRepository.save(entityToSave);

        return UserMapper.INSTANCE.userToUserResponseDto(entityToSave);
    }
}
