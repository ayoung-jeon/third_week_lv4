package com.sparta.sparta.repository;

import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.LectureLike;
import com.sparta.sparta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureLikeRepository extends JpaRepository<LectureLike, Long> {
    Optional<LectureLike> findByLectureAndUser(Lecture lecture, User user);
    long countByLecture(Lecture lecture);
}
