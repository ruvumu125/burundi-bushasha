package com.burundibuhire.burundi.buhire.controller.api;

import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationRequest;
import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationResponse;
import com.burundibuhire.burundi.buhire.utils.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/authentication")
public interface AuthenticationApi {

    @PostMapping(value = Constants.AUTHENTICATION_ENDPOINT + "/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request);

    @PostMapping(value = Constants.AUTHENTICATION_ENDPOINT + "/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
