package com.growit.api.service;

import com.growit.api.domain.User;
import com.growit.api.dto.AuthDto;
import com.growit.api.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static org.springframework.http.ResponseEntity.ok;


@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserService userDetailsService;
    private final InvestorService investorService;
    private final BorrowerService borrowerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, TokenProvider tokenProvider, UserService userDetailsService, InvestorService investorService, BorrowerService borrowerService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
        this.investorService = investorService;
        this.borrowerService = borrowerService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity signIn(AuthDto creds) {
        String username = creds.getUsername();
        User user = userDetailsService.findByUsernameAndPassword(username, passwordEncoder.encode(creds.getPassword()));
/*
        User user;
        String username;
        String first = new StringBuilder(creds.getUsername().charAt(0)).toString();

        if (first.equals("*") || first.equals("&")) {
            username = creds.getUsername().substring(1);
            if (first.equals("*")) {
                user = investorService.loadUserByUsername(username);
            } else {
                user = borrowerService.loadUserByUsername(username);
            }
        } else {
               username = creds.getUsername();
               user = userDetailsService.loadUserByUsername(username);
            }*/

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, creds.getPassword()));
            String token = tokenProvider.createToken(username, user.getRoles().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            model.put("roles", user.getAuthorities());
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
