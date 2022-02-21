package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.book.BookRequest;
import com.getir.readingIsGood.controller.vo.book.BookResponse;
import com.getir.readingIsGood.dto.book.BookRequestDto;
import com.getir.readingIsGood.dto.book.BookResponseDto;
import com.getir.readingIsGood.mappers.BookMapper;
import com.getir.readingIsGood.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.mapstruct.factory.Mappers.getMapper;

@RestController
@RequestMapping(value = "/rig")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper = getMapper(BookMapper.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/addNewBook")
    public ResponseEntity<BookResponse> addNewBook(@RequestBody BookRequest bookRequest) {

        BookRequestDto bookRequestDto = bookMapper.bookRequestToBookRequestDto(bookRequest);
        BookResponseDto bookResponseDto = bookService.addNewBook(bookRequestDto);

        return ResponseEntity.ok(bookMapper.bookResponseDtoToBookResponse(bookResponseDto));
    }

    @PostMapping(value = "/updateBookStock")
    public ResponseEntity<BookResponse> updateBookStock(@RequestParam Long bookId, @RequestParam Long newStockValue) {

        BookResponseDto bookResponseDto = bookService.updateBookStock(bookId, newStockValue);

        return ResponseEntity.ok(bookMapper.bookResponseDtoToBookResponse(bookResponseDto));
    }
}
