package com.blog.domain.user;

import lombok.AllArgsConstructor;
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

    @Column(name="profile_code")
    private Long profileCode;

    @Column(name="phone_number")
    private String phoneNumber;
}
