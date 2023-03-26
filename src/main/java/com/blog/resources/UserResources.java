package com.blog.resources;

import com.blog.auth.AuthenticationResponse;
import com.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserResources {
    private final UserService userService;

    public UserResources(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/activate")
    public ResponseEntity<AuthenticationResponse> changeUserStatus(){
        return  ResponseEntity.ok(userService.activateAccount());
    }
}
