package com.exame.licitagov.configs;

import com.exame.licitagov.repositorys.UserRepository;
import com.exame.licitagov.services.impl.BiddingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDate;

@Configuration
@EnableRetry
public class ApplicationConfiguration {

    @Autowired
    public ApplicationConfiguration(UserRepository userRepository, BiddingServiceImpl biddingService) {
        this.userRepository = userRepository;
        this.biddingService = biddingService;
    }

    private final UserRepository userRepository;


    private final BiddingServiceImpl biddingService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void PrePopulateData() throws IOException {
        //TODO Pensar em um nome melhor para o método e entender se aqui realmente é o lugar dele
        LocalDate publicationDate = LocalDate.now().minusYears(1).minusDays(1);

        biddingService.getBids(parseDateToString(publicationDate));
    }

    private static String parseDateToString(LocalDate date){
        String year = String.valueOf(date.getYear());
        String month = date.getMonthValue() <= 9 ?
                String.valueOf( "0" + date.getMonthValue()) : String.valueOf(date.getMonthValue());
        String day = String.valueOf(date.getDayOfMonth());


        return year.concat(month).concat(day);
    }

}
