package com.sparta.sparta.repository;

import com.sparta.sparta.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByTutorName(String tutorName);
}
