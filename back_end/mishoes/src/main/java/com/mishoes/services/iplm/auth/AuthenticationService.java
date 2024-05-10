package com.mishoes.services.iplm.auth;

import com.mishoes.dtos.requests.auth.AuthenticationRequest;
import com.mishoes.exceptions.DataNotFoundException;
import com.mishoes.repositories.UserRepository;
import com.mishoes.services.IAuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;

    @Override
    public boolean authentication(AuthenticationRequest request) throws DataNotFoundException {
        var user = userRepository.findByUserName(request.getUsername()).orElseThrow(() ->
                     new DataNotFoundException("User name not found")

        );
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
      return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
