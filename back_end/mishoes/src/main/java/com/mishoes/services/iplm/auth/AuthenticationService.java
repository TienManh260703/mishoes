package com.mishoes.services.iplm.auth;

import com.mishoes.dtos.requests.auth.AuthenticationRequest;
import com.mishoes.dtos.requests.auth.IntrospectRequest;
import com.mishoes.dtos.responses.auth.AuthenticationResponse;
import com.mishoes.dtos.responses.auth.IntrospectResponse;
import com.mishoes.exceptions.AppException;
import com.mishoes.exceptions.DataNotFoundException;
import com.mishoes.exceptions.ErrorCode;
import com.mishoes.repositories.UserRepository;
import com.mishoes.services.IAuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
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
    protected static final String KEY = "3lGTsDCc67B9SuDiVEBea6uglcxRtH+rHugZDqhl1CPu6fJgK2kOP9aqJVEu1V2A";

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
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
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
                .issueTime(new Date(
//                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                        24 * 60 * 60
                ))
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
            throw new RuntimeException("Create token fail" );
        }
    }
}
