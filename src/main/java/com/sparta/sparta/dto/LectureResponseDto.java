package com.sparta.sparta.dto;

import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.LectureEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LectureResponseDto {
    private Long lectureId;
    private String title;
    private Long price;
    private String description;
    private LectureEnum category;
//    private String tutorName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public LectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
//        this.tutorName = lecture.getTutorName();
        this.createdAt = lecture.getCreatedAt();
        this.modifiedAt = lecture.getModifiedAt();
    }
}
