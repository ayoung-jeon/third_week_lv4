package com.sparta.sparta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long phoneNumber;

    @NotBlank
    private String bio;
}
