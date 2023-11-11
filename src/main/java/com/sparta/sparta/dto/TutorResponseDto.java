package com.sparta.sparta.dto;

import com.sparta.sparta.entity.Tutor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorResponseDto {
    private Long tutorId;
    private String tutorName;
    private Long experienceYears;
    private String company;
    private String phoneNumber;
    private String bio;

    public TutorResponseDto(Tutor tutor) {
        this.tutorId = tutor.getTutorId();
        this.tutorName = tutor.getTutorName();
        this.experienceYears = tutor.getExperienceYears();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.bio = tutor.getBio();
    }
}
