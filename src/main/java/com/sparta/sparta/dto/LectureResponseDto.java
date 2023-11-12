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
    private Long likeCount; // 좋아요 수 필드 추가
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public LectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
        this.modifiedAt = lecture.getModifiedAt();
    }

    // 좋아요 수를 포함하는 새로운 생성자 추가 (오버로딩 된 생성자)
    public LectureResponseDto(Lecture lecture, Long likeCount) {
        this.lectureId = lecture.getLectureId();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
        this.modifiedAt = lecture.getModifiedAt();
        this.likeCount = likeCount; // 좋아요 수 설정
    }
}
