package com.blog.resources;

import com.blog.auth.AuthenticationResponse;
import com.blog.auth.RegistrationRequest;
import com.blog.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResources {
    private final AuthenticationService authenticationService;

    public AuthResources(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request){
      return ResponseEntity.ok(authenticationService.register(request));
    }
}
