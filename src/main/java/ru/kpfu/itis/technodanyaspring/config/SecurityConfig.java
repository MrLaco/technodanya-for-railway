package ru.kpfu.itis.technodanyaspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/webjars/**", "/static/**").permitAll()
                    .antMatchers("/api/user/").permitAll()
                    .antMatchers("/profile", "/about_premium", "/create_article", "/my_articles", "/articles", "/chat", "/reviews").authenticated()
                    .antMatchers("/login").anonymous()
                    .anyRequest().permitAll().and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/profile")
                    .permitAll()
                    .and()
                .rememberMe()
                    .rememberMeParameter("remember-me")
                    .key("/hY+aVmPUmMSu4ce2t52FosisiTHmHBrM2Zkita4Puk=")
                    .tokenValiditySeconds(86400)
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
        return httpSecurity.build();
    }
}