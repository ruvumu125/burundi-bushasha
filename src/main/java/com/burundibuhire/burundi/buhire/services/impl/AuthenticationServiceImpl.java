package com.burundibuhire.burundi.buhire.services.impl;

import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationRequest;
import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationResponse;
import com.burundibuhire.burundi.buhire.services.AuthenticationService;
import com.burundibuhire.burundi.buhire.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final String token = jwtUtils.generateToken(authentication);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}

