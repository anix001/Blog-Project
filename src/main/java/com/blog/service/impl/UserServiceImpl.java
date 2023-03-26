package com.blog.service.impl;

import com.blog.auth.AuthenticationResponse;
import com.blog.domain.enumeration.UserStatus;
import com.blog.domain.user.AppUser;
import com.blog.dto.user.UserDTO;
import com.blog.dto.user.UserDTOMapper;
import com.blog.repository.UserRepository;
import com.blog.service.AdminService;
import com.blog.service.JwtService;
import com.blog.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final AdminService adminService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserDTOMapper userDTOMapper;

    public UserServiceImpl(AdminService adminService, UserDetailsService userDetailsService, UserRepository userRepository, JwtService jwtService, UserDTOMapper userDTOMapper) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public AuthenticationResponse activateAccount() {
        AppUser user = currentUser();
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        UserDTO userDTO = userDTOMapper.apply(user);
        return new AuthenticationResponse(userDTO, jwtToken);
    }

    @Override
    public AppUser currentUser() {
        UserDetails userDetails = adminService.getCurrentLoggedInUser();
        AppUser user = (AppUser) userDetailsService.loadUserByUsername(userDetails.getUsername());
        return user;
    }
}
