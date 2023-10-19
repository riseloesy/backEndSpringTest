package com.example.testspringboot.controller;

import com.example.testspringboot.dto.BookReqDTO;
import com.example.testspringboot.dto.BookReqForm;
import com.example.testspringboot.dto.BookResDTO;
import com.example.testspringboot.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/bookspage")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("/index")
    public ModelAndView index() {
        List<BookResDTO> bookResDTOList = bookService.getBooks();
        return new ModelAndView("index", "books", bookResDTOList);
    }


    //등록 페이지를 로딩해주는 메서드
    @GetMapping("/signup")
    public String showSignUpForm(BookReqDTO book) {
        return "add-book";
    }

    //입력항목 검증을 하고, 등록 처리를 해주는 메서드
    @PostMapping("/addbook")
    public String addBook(@Valid BookReqDTO book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-book";
        }
        //등록요청
        bookService.saveBook(book);
        return "redirect:/bookspage/index";
    }

    //수정 페이지를 호출해주는 메서드
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book", bookResDTO);
        return "update-book";
    }

    //수정
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid @ModelAttribute("book") BookReqForm book,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "update-book";
        }
        bookService.updateBookForm(book);

        return "redirect:/bookspage/index";

    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return "redirect:/bookspage/index";
    }
}
