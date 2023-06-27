package com.exame.licitagov.controllers;

import com.exame.licitagov.models.request.AuthenticationRequest;
import com.exame.licitagov.models.request.RegisterRequest;
import com.exame.licitagov.models.responses.AuthenticationResponse;
import com.exame.licitagov.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    private final AuthenticationService authenticationService;


    @ResponseBody
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody RegisterRequest authenticationRequest) {
        return authenticationService.register(authenticationRequest);
    }

    @ResponseBody
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

}
