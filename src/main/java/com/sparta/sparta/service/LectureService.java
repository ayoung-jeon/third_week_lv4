package com.sparta.sparta.service;

import com.sparta.sparta.dto.LectureRequestDto;
import com.sparta.sparta.dto.LectureResponseDto;
import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.LectureEnum;
import com.sparta.sparta.entity.Tutor;
import com.sparta.sparta.repository.LectureRepository;
import com.sparta.sparta.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;


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

        // Tutor 조회
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId())
                .orElseThrow(() -> new EntityNotFoundException("Tutor not found"));

        // Lecture 엔티티 생성
        Lecture lecture = new Lecture();
        lecture.setTitle(requestDto.getTitle());
        lecture.setPrice(requestDto.getPrice());
        lecture.setDescription(requestDto.getDescription());
        lecture.setCategory(requestDto.getCategory());
        lecture.setTutor(tutor); // 여기에서 Tutor 설정

        // 강의 저장
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

    // 강의 조회
    public Map<String, Object> getLectureDetails(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("Lecture not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("lectureId", lecture.getLectureId());
        response.put("title", lecture.getTitle());
        response.put("price", lecture.getPrice());
        response.put("description", lecture.getDescription());
        response.put("category", lecture.getCategory());

        Tutor tutor = lecture.getTutor();
        if (tutor != null) {
            Map<String, Object> tutorDetails = new HashMap<>();
            tutorDetails.put("tutorName", tutor.getTutorName());
            tutorDetails.put("experienceYears", tutor.getExperienceYears());
            tutorDetails.put("company", tutor.getCompany());
            // 전화번호는 제외
            response.put("tutor", tutorDetails);
        }

        return response;
    }
}
