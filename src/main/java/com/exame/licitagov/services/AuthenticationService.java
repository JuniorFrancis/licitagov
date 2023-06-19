package com.exame.licitagov.services;

import com.exame.licitagov.models.request.AuthenticationRequest;
import com.exame.licitagov.models.request.RegisterRequest;
import com.exame.licitagov.models.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest authenticationRequest);
}
