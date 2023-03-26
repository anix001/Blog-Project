package com.blog.service.impl;

import com.blog.domain.user.AppUser;
import com.blog.repository.UserRepository;
import com.blog.service.AdminService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AdminServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDetails getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<AppUser> user = userRepository.findByEmail(userDetails.getUsername());
        return this.userDetailsService.loadUserByUsername(user.get().getEmail());
    }
}
