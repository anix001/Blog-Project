package com.blog.service.impl;

import com.blog.auth.AuthenticationRequest;
import com.blog.auth.AuthenticationResponse;
import com.blog.auth.RegistrationRequest;
import com.blog.domain.enumeration.Role;
import com.blog.domain.enumeration.UserStatus;
import com.blog.domain.AppUser;
import com.blog.domain.dto.UserDTO;
import com.blog.service.mapper.UserDTOMapper;
import com.blog.repository.UserRepository;
import com.blog.service.AuthenticationService;
import com.blog.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDTOMapper userDTOMapper;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager,  UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public AuthenticationResponse authResponseGenerator(AppUser user) {
        String jwtToken = jwtService.generateToken(user);
        UserDTO userDTO = userDTOMapper.apply(user);
        return new AuthenticationResponse(userDTO, jwtToken);
    }

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        AppUser user = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .address(request.getAddress())
                .municipalityName(request.getMunicipalityName())
                .phoneNumber(request.getPhoneNumber())
                .userStatus(UserStatus.PENDING)
                .build();

       userRepository.save(user);

        return authResponseGenerator(user);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found !!"));

        return authResponseGenerator(user);
    }


}
