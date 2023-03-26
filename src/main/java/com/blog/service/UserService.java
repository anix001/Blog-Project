package com.blog.service;

import com.blog.auth.AuthenticationResponse;
import com.blog.domain.AppUser;

public interface UserService {
    AuthenticationResponse activateAccount();
    AppUser currentUser();
}
