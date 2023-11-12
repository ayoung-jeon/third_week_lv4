package com.sparta.sparta.controller;

import com.sparta.sparta.security.UserDetailsImpl;
import com.sparta.sparta.service.LectureLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureLikeController {

    private final LectureLikeService lectureLikeService;

    @PostMapping("/{lectureId}/like")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> toggleLike(@PathVariable Long lectureId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        boolean liked = lectureLikeService.toggleLike(lectureId, userId);
        return ResponseEntity.ok(Map.of("liked", liked));
    }
}
