package com.burundibuhire.burundi.buhire.services.auth;

import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationRequest;
import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
