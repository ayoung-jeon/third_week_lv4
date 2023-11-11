package com.sparta.sparta.dto;

import com.sparta.sparta.entity.LectureEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureRequestDto {

    @NotBlank
    private String title;

    @DecimalMin(value = "0", inclusive = false)
    private Long price;

    @NotBlank
    private String description;

    @NonNull
    private LectureEnum category;

//    @NotBlank
//    private String tutorName;

    private Long tutorId;
}
