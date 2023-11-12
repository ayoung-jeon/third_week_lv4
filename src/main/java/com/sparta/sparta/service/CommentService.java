package com.sparta.sparta.service;

import com.sparta.sparta.dto.CommentResponseDto;
import com.sparta.sparta.entity.Comment;
import com.sparta.sparta.entity.Lecture;
import com.sparta.sparta.entity.User;
import com.sparta.sparta.repository.CommentRepository;
import com.sparta.sparta.repository.LectureRepository;
import com.sparta.sparta.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    // 댓글 등록
    public CommentResponseDto createComment(Long lectureId, Long userId, String content) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("Lecture not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Comment comment = new Comment();
        comment.setLecture(lecture);
        comment.setUser(user);
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }

    // 댓글 조회
    public List<CommentResponseDto> getCommentsByLecture(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("Lecture not found"));
        List<Comment> comments = commentRepository.findByLecture(lecture);

        // Comment 객체를 CommentResponseDto로 변환
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    // 댓글 수정 (UserId 비교해서 찾는 코드도 있어야 함)
    public CommentResponseDto updateComment(Long commentId, Long userId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (!comment.getUser().getUserId().equals(userId)) {
            throw new AccessDeniedException("User not authorized to update this comment");
        }

        comment.setContent(newContent);
        Comment updatedComment = commentRepository.save(comment);
        return new CommentResponseDto(updatedComment);
    }

    // 댓글 삭제 (UserId 비교해서 찾는 코드도 있어야 함)
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (!comment.getUser().getUserId().equals(userId)) {
            throw new AccessDeniedException("User not authorized to delete this comment");
        }

        commentRepository.delete(comment);
    }
}
