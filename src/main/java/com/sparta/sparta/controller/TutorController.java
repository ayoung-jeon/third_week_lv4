package com.sparta.sparta.controller;

import com.sparta.sparta.dto.TutorRequestDto;
import com.sparta.sparta.dto.TutorResponseDto;
import com.sparta.sparta.service.TutorService;
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
public class TutorController {

    private final TutorService tutorService;

    // 강사 등록
    @PostMapping("/tutor")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TutorResponseDto> registerTutor(@Valid @RequestBody TutorRequestDto requestDto) {
        TutorResponseDto responseDto = tutorService.registerTutor(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
