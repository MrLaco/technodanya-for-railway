package ru.kpfu.itis.technodanyaspring.service;


import ru.kpfu.itis.technodanyaspring.dto.*;
import ru.kpfu.itis.technodanyaspring.model.*;

import java.util.*;

public interface PremiumService {

    Optional<PremiumDto> findPremiumByUser(User user);
}
