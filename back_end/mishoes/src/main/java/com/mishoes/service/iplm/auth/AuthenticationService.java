package com.mishoes.service.iplm.auth;

import com.mishoes.dto.request.auth.AuthenticationRequest;
import com.mishoes.dto.request.auth.IntrospectRequest;
import com.mishoes.dto.response.auth.AuthenticationResponse;
import com.mishoes.dto.response.auth.IntrospectResponse;
import com.mishoes.exception.AppException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.exception.ErrorCode;
import com.mishoes.repository.UserRepository;
import com.mishoes.service.IAuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}") // Update sau
    protected String KEY;

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        var user = userRepository.findByUserName(request.getUsername()).orElseThrow(() ->
                {
                    throw new DataNotFoundException("User name not found");
                }

        );
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(request.getUsername(), user.getId());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        String token = request.getToken();
        JWSVerifier verifier = new MACVerifier(KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        //

        Date expiredTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        return IntrospectResponse
                .builder()
                .valid(verified && expiredTime.after(new Date()))
                .build();
    }


    private String generateToken(String username, String id) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        // Tạo đc payload cần có claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("manhntph37150")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(24, ChronoUnit.HOURS)))
                .claim("userId", id)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        // Ký token
        try {
            jwsObject.sign(new MACSigner(KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("Cannot create token" + e.getMessage());
            throw new RuntimeException("Create token fail");
        }
    }
}
