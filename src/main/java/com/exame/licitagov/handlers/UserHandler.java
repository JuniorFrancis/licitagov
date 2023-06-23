package com.exame.licitagov.handlers;

import com.exame.licitagov.models.User;
import com.exame.licitagov.repositorys.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        return userRepository.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User not found"));

    }
}
