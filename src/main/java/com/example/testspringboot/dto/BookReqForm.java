package com.example.testspringboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class BookReqForm {

    private Long id;

    @NotEmpty(message = "제목은 필수입력항목입니다.") // " " 허용
    private String title;

    @NotBlank(message = "ISBN은 필수입력항목입니다.") // " " 허용하지 않음
    private String isbn;;

}
