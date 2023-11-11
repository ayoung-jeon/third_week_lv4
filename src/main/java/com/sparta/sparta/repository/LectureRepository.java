package com.sparta.sparta.repository;

import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.LectureEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    boolean existsByTitle(String title);

    // 카테고리별 강의 목록 조회

    // 강의명으로 오름차순, 내림차순 정렬
    List<Lecture> findByCategoryOrderByTitleAsc(LectureEnum category);
    List<Lecture> findByCategoryOrderByTitleDesc(LectureEnum category);

    // 가격으로 오름차순, 내림차순 정렬
    List<Lecture> findByCategoryOrderByPriceAsc(LectureEnum category);
    List<Lecture> findByCategoryOrderByPriceDesc(LectureEnum category);

    // 등록일로 오름차순, 내림차순 정렬
    List<Lecture> findByCategoryOrderByCreatedAtAsc(LectureEnum category);
    List<Lecture> findByCategoryOrderByCreatedAtDesc(LectureEnum category);

}
