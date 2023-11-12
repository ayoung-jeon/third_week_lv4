package com.sparta.sparta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true) // unique = true -> 중복을 막는다
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)  // Enum 타입을 데이터 베이스 컬럼에 저장할 때 사용함
    private UserRoleEnum role;

    public User(String username, String email, String password, String gender, String phoneNumber, String address, String company, UserRoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.company = company;
        this.role = role;
    }
}
