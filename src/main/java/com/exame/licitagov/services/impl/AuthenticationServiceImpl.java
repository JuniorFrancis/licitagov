package com.exame.licitagov.services.impl;

import com.exame.licitagov.constants.Role;
import com.exame.licitagov.exceptions.AlreadyExistingUsernameException;
import com.exame.licitagov.models.User;
import com.exame.licitagov.models.request.AuthenticationRequest;
import com.exame.licitagov.models.request.RegisterRequest;
import com.exame.licitagov.models.responses.AuthenticationResponse;
import com.exame.licitagov.repositorys.UserRepository;
import com.exame.licitagov.services.AuthenticationService;
import com.exame.licitagov.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.exame.licitagov.validators.Validators.isValidCredentials;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder encoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow( () -> new UsernameNotFoundException("User not found"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse.Builder()
                .withToken(jwtToken)
                .build();

    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        isValidCredentials(request.getUsername(), request.getPassword());

        boolean isAlreadyRegistered = userRepository.existsUserByUsername(request.getUsername());

        if(isAlreadyRegistered) {
            throw new AlreadyExistingUsernameException("Already existing username");
        }

        User user = new User.Builder()
                .withUsername(request.getUsername())
                .withPassword(encoder.encode(request.getPassword()))
                .withStatus(true)
                .withRole(Role.COMMON)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse.Builder()
                .withToken(jwtToken)
                .build();


    }
}
