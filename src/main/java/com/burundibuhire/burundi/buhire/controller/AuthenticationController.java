package com.burundibuhire.burundi.buhire.controller;

import com.burundibuhire.burundi.buhire.controller.api.AuthenticationApi;
import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationRequest;
import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationResponse;
import com.burundibuhire.burundi.buhire.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
