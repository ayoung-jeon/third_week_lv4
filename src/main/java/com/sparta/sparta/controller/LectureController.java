package com.sparta.sparta.controller;

import com.sparta.sparta.dto.LectureRequestDto;
import com.sparta.sparta.dto.LectureResponseDto;
import com.sparta.sparta.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {

    private final LectureService lectureService;

    // 강의 등록
    @PostMapping("/lecture")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<LectureResponseDto> registerLecture(@Valid @RequestBody LectureRequestDto requestDto) {
        LectureResponseDto responseDto = lectureService.registerLecture(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
