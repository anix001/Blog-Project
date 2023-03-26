package com.blog.auth;

import com.blog.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private UserDTO userDTO;
    private String token;
}
