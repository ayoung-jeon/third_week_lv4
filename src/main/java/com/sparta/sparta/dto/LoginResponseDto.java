package com.sparta.sparta.dto;

import com.sparta.sparta.entity.User;
import com.sparta.sparta.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private Long userid;
    private String username;
    private String email;
    private String gender;
    private String phoneNumber;
    private String address;
    private UserRoleEnum role;

    public LoginResponseDto(User user, String accessToken) {
        this.userid = user.getUserid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.role = user.getRole();
    }
}
