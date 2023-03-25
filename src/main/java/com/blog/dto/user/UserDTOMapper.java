package com.blog.dto.user;

import com.blog.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getFirstName(),
                user.getEmail(),
                user.getRole(),
                user.getUserProfile().getAddress(),
                user.getUserProfile().getMunicipalityName(),
                user.getUserProfile().getProfileCode(),
                user.getUserProfile().getPhoneNumber()
        );
    }
}
