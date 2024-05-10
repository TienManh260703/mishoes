package com.mishoes.controllers.auth;

import com.mishoes.dtos.requests.auth.AuthenticationRequest;
import com.mishoes.dtos.responses.auth.AuthenticationResponse;
import com.mishoes.exceptions.DataNotFoundException;
import com.mishoes.services.iplm.auth.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("log-in")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request) throws DataNotFoundException {
        boolean result = authenticationService.authentication(request);
        return ResponseEntity.ok().body(
                AuthenticationResponse.builder()
                        .authenticated(result)
                        .build()
        );
    }
}
