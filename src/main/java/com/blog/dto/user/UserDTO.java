package com.blog.dto.user;

import com.blog.domain.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String address;
    private String municipalityName;
    private Long profileCode;
    private String phoneNumber;
}
