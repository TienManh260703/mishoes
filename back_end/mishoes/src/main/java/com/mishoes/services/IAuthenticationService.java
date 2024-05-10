package com.mishoes.services;

import com.mishoes.dtos.requests.auth.AuthenticationRequest;
import com.mishoes.dtos.requests.auth.IntrospectRequest;
import com.mishoes.dtos.responses.auth.AuthenticationResponse;
import com.mishoes.dtos.responses.auth.IntrospectResponse;
import com.mishoes.exceptions.DataNotFoundException;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request) throws DataNotFoundException;
    IntrospectResponse introspect (IntrospectRequest request) throws JOSEException, ParseException;
}
