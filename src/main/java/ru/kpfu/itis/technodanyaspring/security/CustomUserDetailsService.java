package ru.kpfu.itis.technodanyaspring.security;

import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import ru.kpfu.itis.technodanyaspring.repository.*;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
