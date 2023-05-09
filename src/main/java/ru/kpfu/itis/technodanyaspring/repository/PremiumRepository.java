package ru.kpfu.itis.technodanyaspring.repository;

import org.springframework.data.jpa.repository.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface PremiumRepository extends JpaRepository<Premium, Integer> {

    Optional<Premium> findPremiumByUser(User user);
}
