package com.mishoes.controller.auth;

import com.mishoes.dto.request.auth.AuthenticationRequest;
import com.mishoes.dto.request.auth.IntrospectRequest;
import com.mishoes.dto.request.auth.LogoutRequest;
import com.mishoes.dto.request.auth.RefreshTokenRequest;
import com.mishoes.dto.response.auth.AuthenticationResponse;
import com.mishoes.exception.DataNotFoundException;

import com.mishoes.service.iplm.auth.AuthenticationService;

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
    public ResponseEntity<?> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ResponseEntity.ok().body(
                result
        );
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ResponseEntity.ok().body("Logout successful");
    }
    @PostMapping("refresh")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody RefreshTokenRequest request)
            throws DataNotFoundException, ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ResponseEntity.ok().body(
                result
        );
    }
}
