package com.blog.service;

import com.blog.auth.AuthenticationRequest;
import com.blog.auth.AuthenticationResponse;
import com.blog.auth.RegistrationRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegistrationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
    AuthenticationResponse activateAccount();
}
