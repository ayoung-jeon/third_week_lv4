package com.sparta.sparta.service;

import com.sparta.sparta.dto.LectureRequestDto;
import com.sparta.sparta.dto.LectureResponseDto;
import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.LectureEnum;
import com.sparta.sparta.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;

    public LectureResponseDto registerLecture(LectureRequestDto requestDto) {
        // 로그 남기기
        log.info("Registering a new lecture with title: {}", requestDto.getTitle());

        // 중복 강의 체크
        if (lectureRepository.existsByTitle(requestDto.getTitle())) {
            // 중복 강의가 있는 경우 로그를 남기고 예외를 발생.
            log.warn("Lecture registration failed: Duplicate lecture title {}", requestDto.getTitle());
            throw new IllegalStateException("Lecture with title " + requestDto.getTitle() + " already exists");
        }

        // category 유효성 검사
        // Enum 값 검증
        if (!EnumSet.allOf(LectureEnum.class).contains(requestDto.getCategory())) {
            throw new IllegalArgumentException("Invalid category: " + requestDto.getCategory());
        }

        // Lecture 엔티티 생성
        Lecture lecture = new Lecture(requestDto);
        Lecture savedLecture;

        // 저장 시도
        try {
            savedLecture = lectureRepository.save(lecture);
        } catch (DataAccessException e) {
            // 데이터베이스 액세스 중 예외 발생 시 로그를 발생
            log.error("Lecture registration failed: Database access error", e);
            throw e; // 예외를 상위 계층으로 전파
        }

        // 저장 성공 로그
        log.info("Lecture registered successfully with id: {}", savedLecture.getLectureId());

        // LectureResponseDto 생성 및 반환
        return new LectureResponseDto(savedLecture);
    }
}
