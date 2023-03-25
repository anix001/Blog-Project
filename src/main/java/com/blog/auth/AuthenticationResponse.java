package com.blog.auth;

import com.blog.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UserDTO userDTO;
//    private String token;
}
