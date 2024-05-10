package com.mishoes.controllers.auth;

import com.mishoes.dtos.requests.auth.AuthenticationRequest;
import com.mishoes.dtos.requests.auth.IntrospectRequest;
import com.mishoes.dtos.responses.auth.AuthenticationResponse;
import com.mishoes.exceptions.DataNotFoundException;
import com.mishoes.services.iplm.auth.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("token")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) throws DataNotFoundException {
        var result = authenticationService.authentication(request);
        return ResponseEntity.ok().body(
               result
        );
    }

    @PostMapping("introspect")
    public ResponseEntity<?> authenticate (@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ResponseEntity.ok().body(
                result
        );
    }

}
