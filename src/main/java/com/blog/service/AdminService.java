package com.blog.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {
    UserDetails getCurrentLoggedInUser();
}
