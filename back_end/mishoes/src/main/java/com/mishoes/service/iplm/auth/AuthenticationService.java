package com.mishoes.service.iplm.auth;

import com.mishoes.dto.request.auth.AuthenticationRequest;
import com.mishoes.dto.request.auth.IntrospectRequest;
import com.mishoes.dto.request.auth.LogoutRequest;
import com.mishoes.dto.request.auth.RefreshTokenRequest;
import com.mishoes.dto.response.auth.AuthenticationResponse;
import com.mishoes.dto.response.auth.IntrospectResponse;
import com.mishoes.entity.InvalidatedToken;
import com.mishoes.entity.User;
import com.mishoes.exception.AppException;
import com.mishoes.exception.DataNotFoundException;
import com.mishoes.exception.ErrorCode;
import com.mishoes.repository.InvalidatedTokenRepository;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    @NonFinal
    @Value("${jwt.signerKey}") // Update sau
    protected String KEY;
    // Đang chưa lấy đc value từ file cấu hình -> mới phải gán
    @Value(("${jwt.valid-duration}"))
    private long VALID_DURATION=86_400;
    @Value("${jwt.refreshable-duration}")
    private long REFRESHABLE_DURATION=172_800;

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        var user = userRepository.findByUserName(request.getUsername()).orElseThrow(() -> {
                    throw new DataNotFoundException("User name not found");
                }
        );
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),
                user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    // Xem token còn hạn không
    @Override
    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        String token = request.getToken();
        boolean isValid = true;
        try {
            SignedJWT jwtToken = verifyToken(token, false);
        } catch (AppException exception) {
            isValid = false;
        }
        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }


    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        // Tạo đc payload cần có claim
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("manhntph37150")
                .issueTime(new Date())
                .expirationTime(Date.from(Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS)))
                .jwtID(UUID.randomUUID().toString())
                .claim("userId", user.getId())
                .claim("scope", buildScope(user))
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

    // Chuyển SOPE_ -> ROLE_ và Lấy ra role và các permission của user
    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        return stringJoiner.toString();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        // Read token
        try {
            SignedJWT signedJWT = verifyToken(request.getToken(), true);
            String jti = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken
                    .builder()
                    .id(jti)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepository.save(invalidatedToken);
        } catch (AppException exception) {
            log.info("Token already expired");
        }
    }

    // Lấy ra thông tin chi tiết token
    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        //
        Date expiredTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        // Nếu token hết hạn
        if (!(verified && expiredTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        // Kiểm tra xem token này đã logout chưa
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }

    // gia hạn token
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        // logout token cũ
        SignedJWT signedJWT = verifyToken(request.getToken(), true);
        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jti)
                .expiryTime(expiryTime)
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
        String username = signedJWT.getJWTClaimsSet().getSubject();
        User user = userRepository.findByUserName(username).orElseThrow(() ->
        {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        });
        // Tạo token mới
        String token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
}
