package com.sparta.sparta.repository;

import com.sparta.sparta.entity.Comment;
import com.sparta.sparta.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByLecture(Lecture lecture);
}
