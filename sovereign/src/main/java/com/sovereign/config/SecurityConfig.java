package com.sovereign.config;

import com.sovereign.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService UserService;

    public SecurityConfig(UserService userService) {
        this.UserService = userService;
    }

    // ── Password encoder bean ──
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ── Auth provider — links UserService + PasswordEncoder ──
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(UserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authProvider())
                .authorizeHttpRequests(auth -> auth

                        // ── PUBLIC pages — no login needed ──
                        .requestMatchers(
                                "/",
                                "/catalog",
                                "/catalog/**",
                                "/cars/**",
                                "/configurator/**",
                                "/user/login",
                                "/user/register",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/h2-console/**"
                        ).permitAll()

                        // ── PROTECTED pages — must be logged in ──
                        // booking, wishlist, saving a config → login required
                        .requestMatchers(
                                "/booking/**",
                                "/wishlist/**",
                                "/configurator/*/save"
                        ).authenticated()

                        // everything else is public too
                        .anyRequest().permitAll()
                )

                // ── Login page config ──
                .formLogin(form -> form
                        .loginPage("/user/login")          // your custom login page
                        .loginProcessingUrl("/user/login") // form posts HERE
                        .defaultSuccessUrl("/", true)      // after login → homepage
                        .failureUrl("/user/login?error=true") // wrong password → back to login
                        .permitAll()
                )

                // ── Logout ──
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )

                // ── Disable CSRF for H2 console (dev only) ──
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )

                // ── Allow H2 console frames ──
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }
}