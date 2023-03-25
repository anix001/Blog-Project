package com.blog.domain.user;

import com.blog.domain.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="address")
    private String address;

    @Column(name="municipality_name")
    private String municipalityName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public UserProfile(String address, String municipalityName, String phoneNumber, UserStatus userStatus) {
    }
}
