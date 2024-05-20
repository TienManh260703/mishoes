package com.mishoes.configuration;

import com.mishoes.entity.User;
import com.mishoes.enums.Role;
import com.mishoes.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationIntConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                LocalDate dateOfBirth = LocalDate.parse("2003-12-12");
                User user = User.builder()
                        .userName("admin")
                        .lastName("Mạnh")
                        .firstName("Nguyễn Tiến")
                        .dateOfBirth(dateOfBirth)
//                        .roles(roles)
                        .password(passwordEncoder.encode("admin"))
                        .build();
                userRepository.save(user);
                log.warn("|--> Default admin has been created with default password : admin , please change it");
            }
        };
    }
}
