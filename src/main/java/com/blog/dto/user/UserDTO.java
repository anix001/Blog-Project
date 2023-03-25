package com.blog.dto.user;

import com.blog.domain.enumeration.Role;
import com.blog.domain.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String address;
    private String municipalityName;
    private String phoneNumber;
    private UserStatus userStatus;
}
