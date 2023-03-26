package com.blog.service.mapper;

import com.blog.domain.AppUser;
import com.blog.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<AppUser, UserDTO> {
    @Override
    public UserDTO apply(AppUser appUser) {
        return new UserDTO(
                appUser.getId(),
                appUser.getFirstName(),
                appUser.getFirstName(),
                appUser.getEmail(),
                appUser.getRole(),
                appUser.getAddress(),
                appUser.getMunicipalityName(),
                appUser.getPhoneNumber(),
                appUser.getUserStatus()
        );
    }
}
