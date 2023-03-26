package com.blog.service;

import com.blog.auth.AuthenticationRequest;
import com.blog.auth.AuthenticationResponse;
import com.blog.auth.RegistrationRequest;
import com.blog.domain.user.AppUser;

public interface AuthenticationService {
    AuthenticationResponse authResponseGenerator(AppUser user);
    AuthenticationResponse register(RegistrationRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
