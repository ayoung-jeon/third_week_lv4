package com.sparta.sparta.service;

import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.LectureLike;
import com.sparta.sparta.entity.User;
import com.sparta.sparta.repository.LectureLikeRepository;
import com.sparta.sparta.repository.LectureRepository;
import com.sparta.sparta.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureLikeService {

    private final LectureLikeRepository lectureLikeRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    public boolean toggleLike(Long lectureId, Long userId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("Lecture not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Optional<LectureLike> lectureLike = lectureLikeRepository.findByLectureAndUser(lecture, user);

        if (lectureLike.isPresent()) {
            lectureLikeRepository.delete(lectureLike.get());
            return false; // 좋아요 취소
        } else {
            LectureLike newLike = LectureLike.builder().lecture(lecture).user(user).build();

            lectureLikeRepository.save(newLike);
            return true; // 좋아요 등록
        }
    }
}
