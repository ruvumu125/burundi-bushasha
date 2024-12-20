package com.burundibuhire.burundi.buhire.services;

import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationRequest;
import com.burundibuhire.burundi.buhire.dto.auth.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
