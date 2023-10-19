package com.example.testspringboot.controller;


import com.example.testspringboot.entity.Book;
import com.example.testspringboot.exception.BusinessException;
import com.example.testspringboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class BookSimpleRestController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        Optional<Book> optionalUser = bookRepository.findById(id);
        Book book = optionalUser.orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return book;
    }

    @GetMapping("/isbn/{isbn}")
    public Book getUserByEmail(@PathVariable String isbn){
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(()-> new BusinessException("요청하신 isbn에 해당하는 도서가 없습니다.", HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
//return ResponseEntity.ok(user);
        return ResponseEntity.ok(id + "도서가 삭제되었습니다.");
    }

    @PutMapping("/{id}")
    public Book updatedBook(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book book = bookRepository.findById(id)  //Optional
                .orElseThrow(() -> new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
        //수정하려는 값을 저장
        book.setIsbn(bookDetail.getIsbn());
        book.setTitle(bookDetail.getTitle());
        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }


}