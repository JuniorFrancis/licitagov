package com.exame.licitagov.services.impl;

import com.exame.licitagov.exceptions.AlreadyExistingUsernameException;
import com.exame.licitagov.exceptions.InvalidCredentialsException;
import com.exame.licitagov.models.request.AuthenticationRequest;
import com.exame.licitagov.models.request.RegisterRequest;
import com.exame.licitagov.repositorys.UserRepository;
import com.exame.licitagov.services.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationServiceImplTest {

    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @Mock
    JwtService jwtService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterWithInvalidValues() {
        assertThrows(InvalidCredentialsException.class, () -> {
            authenticationService.register(
                    new RegisterRequest(
                            "",
                            ""
                    )
            );
        });
    }

    @Test
    void testAuthenticateWithNonexistentUser() {

        assertThrows(UsernameNotFoundException.class, () -> {
            authenticationService.authenticate(
                    new AuthenticationRequest(
                            "exemplo",
                            "exemplo"
                    )
            );
        });
    }
}