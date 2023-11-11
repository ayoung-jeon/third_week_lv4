package com.sparta.sparta.service;

import com.sparta.sparta.dto.SignupRequestDto;
import com.sparta.sparta.entity.User;
import com.sparta.sparta.entity.UserRoleEnum;
import com.sparta.sparta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String company = requestDto.getCompany();
        String gender = requestDto.getGender();
        String phoneNumber = requestDto.getPhoneNumber();
        String address = requestDto.getAddress();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        // company 입력에 따라 admin 인지 확인
        UserRoleEnum role; // 권한을 저장할 변수 선언

        // 담당자인 경우 ADMIN 권한 부여
        if ("sparta".equals(company)) {
            role = UserRoleEnum.ADMIN;
        } else {
            // 그 외의 경우는 USER 권한 부여
            role = UserRoleEnum.USER;
        }

        // 사용자 등록
        User user = new User(username, email, password, gender, phoneNumber, address, company, role);
        userRepository.save(user);
        return user;
    }
}
