package com.mishoes.service;

import com.mishoes.dto.request.auth.AuthenticationRequest;
import com.mishoes.dto.request.auth.IntrospectRequest;
import com.mishoes.dto.response.auth.AuthenticationResponse;
import com.mishoes.dto.response.auth.IntrospectResponse;
import com.mishoes.exception.DataNotFoundException;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request) throws DataNotFoundException;
    IntrospectResponse introspect (IntrospectRequest request) throws JOSEException, ParseException;
}
