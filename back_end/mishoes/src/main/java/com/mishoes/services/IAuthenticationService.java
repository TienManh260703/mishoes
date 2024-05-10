package com.mishoes.services;

import com.mishoes.dtos.requests.auth.AuthenticationRequest;
import com.mishoes.exceptions.DataNotFoundException;

public interface IAuthenticationService {
    boolean authentication(AuthenticationRequest request) throws DataNotFoundException;
}
