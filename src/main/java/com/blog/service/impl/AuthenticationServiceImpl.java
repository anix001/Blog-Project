package com.blog.service.impl;

import com.blog.auth.AuthenticationRequest;
import com.blog.auth.AuthenticationResponse;
import com.blog.auth.RegistrationRequest;
import com.blog.domain.enumeration.Role;
import com.blog.domain.enumeration.UserStatus;
import com.blog.domain.user.AppUser;
import com.blog.domain.user.UserProfile;
import com.blog.dto.user.UserDTO;
import com.blog.dto.user.UserDTOMapper;
import com.blog.repository.UserRepository;
import com.blog.service.AuthenticationService;
import com.blog.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDTOMapper userDTOMapper;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserProfile userProfile, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        UserProfile userProfile1 = new UserProfile(request.getAddress(), request.getMunicipalityName(), request.getPhoneNumber(), UserStatus.PENDNING);
       var user = AppUser.builder()
               .firstName(request.getFirstName())
               .lastName(request.getLastName())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .role(Role.USER)
               .userProfile(userProfile1)
               .build();

       userRepository.save(user);

//       var jwtToken = jwtService.generateToken(user);

       UserDTO userDTO = userDTOMapper.apply(user);

        return new AuthenticationResponse(userDTO);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        return null;
    }

    @Override
    public AuthenticationResponse activateAccount() {
        return null;
    }
}
