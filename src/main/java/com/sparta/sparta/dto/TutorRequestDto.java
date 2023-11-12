package com.sparta.sparta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorRequestDto {
    @NotBlank
    private String tutorName;

    @NonNull
    private Long experienceYears;

    @NotBlank
    private String company;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String bio;
}
