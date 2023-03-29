package com.blog.service.impl;

import com.blog.auth.AuthenticationResponse;
import com.blog.domain.enumeration.UserStatus;
import com.blog.domain.AppUser;
import com.blog.domain.dto.UserDTO;
import com.blog.service.mapper.UserDTOMapper;
import com.blog.repository.UserRepository;
import com.blog.service.AdminService;
import com.blog.service.JwtService;
import com.blog.service.UserService;
import com.blog.service.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserServiceImpl(AdminService adminService, UserDetailsService userDetailsService, UserRepository userRepository, JwtService jwtService, UserDTOMapper userDTOMapper, UserMapper userMapper) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userDTOMapper = userDTOMapper;
        this.userMapper = userMapper;
    }

    @Override
    public AuthenticationResponse activateAccount() {
        AppUser user = currentUser();
        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        UserDTO userDTO = userMapper.domainTODto(user);
        return new AuthenticationResponse(userDTO, jwtToken);
    }

    @Override
    public AppUser currentUser() {
        UserDetails userDetails = adminService.getCurrentLoggedInUser();
        AppUser user = (AppUser) userDetailsService.loadUserByUsername(userDetails.getUsername());
        return user;
    }
}
