package com.sparta.sparta.entity;

import com.sparta.sparta.dto.LectureRequestDto;
import com.sparta.sparta.dto.TutorRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lectures")
public class Lecture extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private LectureEnum category;

    @Column(nullable = false)
    private String tutorName;

    public Lecture(LectureRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
        this.tutorName = requestDto.getTutorName();
    }
}
