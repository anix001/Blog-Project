package com.blog.dto.user;

import com.blog.domain.user.AppUser;
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
                appUser.getUserProfile().getAddress(),
                appUser.getUserProfile().getMunicipalityName(),
                appUser.getUserProfile().getPhoneNumber(),
                appUser.getUserProfile().getUserStatus()
        );
    }
}
