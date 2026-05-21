package com.tw.hotel.config;

import com.tw.hotel.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/login", "/api/search/**", "/api/users/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(fl -> fl.loginProcessingUrl("/login"))
        ;

        return http.build();
    }

    @Bean
    public AuthService appUserDetailsService(PasswordEncoder passwordEncoder) {
        ConcurrentHashMap<String, UserDetails> users = new ConcurrentHashMap<>();
        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder::encode)
                .username("test")
                .password("test@1234")
                .build();

        users.put(user.getUsername(), user);
        return new AuthService(users,passwordEncoder);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

}
