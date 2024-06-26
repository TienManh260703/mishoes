package com.mishoes.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Phần quyền bằng method
public class SecurityConfig {
    @Value("${api.prefix}")
    private String API_PREFIX;
    //    @Value("${jwt.signerKey}") // Update sau
//    private String signerKey;
    @Autowired
    private CustomJwtDecoder customJwtDecoder;
    private final String[] PUBLIC_ENDPOINTS = {
            API_PREFIX + "/users",
            API_PREFIX + "/auth/token",
            API_PREFIX + "/auth/introspect",
            API_PREFIX + "/auth/logout",
            API_PREFIX + "/auth/refresh"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                HttpMethod.POST, API_PREFIX + "/users", API_PREFIX + "/auth/token", API_PREFIX + "/auth/introspect", API_PREFIX + "/auth/logout",  API_PREFIX + "/auth/refresh").permitAll()
                                .requestMatchers(API_PREFIX + "/products/**").permitAll()
                                .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(
                oauth2 ->
                        oauth2.jwt(jwtConfigurer ->
                                jwtConfigurer.decoder(customJwtDecoder)
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()) //authentication fail
        );
        // Tắt csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
    // chịu trách nhiệm verify token : (Khong dung) Update sanh CustomJwtDecoder
//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512).build();
//        return nimbusJwtDecoder;
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");// Đã chuyển dổi SCOPE - ROLE_ bên Service
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public CorsFilter corsFilter (){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");// Đứng từ trang web nào để truy cập api( có thể chuyển thành * để tất cả các origin có thể truy cập)
        corsConfiguration.addAllowedMethod("*");// methed nào được gọi tử cái orgin
        corsConfiguration.addAllowedHeader("*");// Tất cả header đc đi qua


        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);// Tất cả các employer đi qua

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
