package com.sparta.sparta.dto;

import com.sparta.sparta.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private String userName;  // 댓글 작성자의 이름 또는 사용자 식별 정보

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getUsername();
    }
}
