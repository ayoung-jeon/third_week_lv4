package com.sparta.sparta.repository;

import com.sparta.sparta.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    boolean existsByTitle(String title);
}
