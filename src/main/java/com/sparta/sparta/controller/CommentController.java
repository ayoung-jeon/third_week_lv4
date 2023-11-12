package com.sparta.sparta.controller;

import com.sparta.sparta.dto.CommentRequestDto;
import com.sparta.sparta.dto.CommentResponseDto;
import com.sparta.sparta.security.UserDetailsImpl;
import com.sparta.sparta.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping("/lectures/{lectureId}/comments")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long lectureId,
                                                         @RequestBody CommentRequestDto requestDto,
                                                         Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUserId(); // UserDetailsImpl에 getUserId() 메서드 구현 필요
        CommentResponseDto commentResponseDto = commentService.createComment(lectureId, userId, requestDto.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

    // 댓글 조회
    @GetMapping("/user/lectures/{lectureId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long lectureId) {
        List<CommentResponseDto> comments = commentService.getCommentsByLecture(lectureId);
        return ResponseEntity.ok(comments);
    }
}
