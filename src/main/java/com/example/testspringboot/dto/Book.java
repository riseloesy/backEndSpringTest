package com.example.testspringboot.dto;

import lombok.*;

@NoArgsConstructor //기본생성자
@AllArgsConstructor //오버로딩 된 생성자
@Getter // getter
@Setter // setter
@ToString // toString
@Builder //객체가 많을때 유연성을 주기 위해 사용하는 것
public class Book {
    private String title;
    private String isbn;
}