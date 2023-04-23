package ru.kpfu.itis.technodanyaspring.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(Integer id);
}
