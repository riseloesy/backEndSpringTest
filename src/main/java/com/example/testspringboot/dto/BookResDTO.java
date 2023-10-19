package com.example.testspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class BookResDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private LocalDateTime createdAt;

}
