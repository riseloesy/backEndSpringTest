package com.example.testspringboot.service;

import com.example.testspringboot.dto.BookReqDTO;
import com.example.testspringboot.dto.BookReqForm;
import com.example.testspringboot.dto.BookResDTO;
import com.example.testspringboot.entity.Book;
import com.example.testspringboot.exception.BusinessException;
import com.example.testspringboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;


    public BookResDTO saveBook(BookReqDTO bookReqDto) {
        //reqDto => entity 매핑
        Book book = modelMapper.map(bookReqDto, Book.class);
        //DB에 저장
        Book savedBook = bookRepository.save(book);
        //entity => resDto //등록된 entity를 dto로 매핑
        return modelMapper.map(savedBook, BookResDTO.class);
    }

    @Transactional(readOnly = true)
    public BookResDTO getBookById(Long id){
        Book bookEntity = bookRepository.findById(id) //Optional<Book>
                .orElseThrow(() -> new BusinessException(id + "Book Not Fount", HttpStatus.NOT_FOUND));
        //Entity -> ResDTO
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public List<BookResDTO> getBooks(){
        List<Book> bookList = bookRepository.findAll();

        List<BookResDTO> bookResDTOList = bookList.stream()
                .map(book -> modelMapper.map(book, BookResDTO.class))
                .collect(toList());
        return bookResDTOList;
    }

    public BookResDTO updateBook(String isbn, BookReqDTO bookReqDto) {
        Book existBook = bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->
                        new BusinessException(isbn + " Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqDto.getTitle());
        return modelMapper.map(existBook, BookResDTO.class);
    }

    public void updateBookForm(BookReqForm bookReqForm) {
        Book existBook = bookRepository.findById(bookReqForm.getId())
                .orElseThrow(() ->
                        new BusinessException(bookReqForm.getId() + " Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqForm.getTitle());
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id) //Optional<Book>
                .orElseThrow(() ->
                        new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(book);
    }






}
