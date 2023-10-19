package com.example.testspringboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class BookReqDTO {
    @NotEmpty(message = "제목은 필수입력항목입니다.") // " " 허용
    private String title;

    @NotEmpty(message = "작가는 필수입력항목입니다.") // " " 허용
    private String author;

    @NotBlank(message = "ISBN은 필수입력항목입니다.") // " " 허용하지 않음
    private String isbn;

}
