package ru.kpfu.itis.technodanyaspring.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.exception.*;
import ru.kpfu.itis.technodanyaspring.service.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class RestUserController {

    private final UserService userService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody CreateUserRequestDto createUserRequestDto
    ) {
        UserResponseDto userResponseDto = userService.create(createUserRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    // READ
    @GetMapping("/get/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer userId) {
        Optional<UserResponseDto> response = userService.findById(userId);

        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Integer userId,
            @RequestBody CreateUserRequestDto user
    ) throws UserNotFoundException {
        UserResponseDto response = userService.update(userId, user);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }
}
