package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.book.BookRequest;
import com.getir.readingIsGood.controller.vo.book.BookResponse;
import com.getir.readingIsGood.dto.book.BookRequestDto;
import com.getir.readingIsGood.dto.book.BookResponseDto;
import com.getir.readingIsGood.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    public void addNewBook() {

        BookResponse expectedResponse = BookResponse.builder()
                .bookId(1L)
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(100L)
                .creationDate(Date.from(Instant.now()))
                .build();

        BookResponseDto bookResponseDto = BookResponseDto.builder()
                .bookId(1L)
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(100L)
                .creationDate(Date.from(Instant.now()))
                .build();

        BookRequest bookRequest = BookRequest.builder()
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(100L)
                .build();

        when(bookService.addNewBook(any(BookRequestDto.class))).thenReturn(bookResponseDto);

        ResponseEntity<BookResponse> result = bookController.addNewBook(bookRequest);

        Assert.assertEquals(expectedResponse.getBookId(), Objects.requireNonNull(result.getBody()).getBookId());
    }

    @Test
    public void updateBookStock() {
        Long bookId = 1L;
        Long newBookStock = 333L;

        BookResponse expectedResponse = BookResponse.builder()
                .bookId(1L)
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(333L)
                .creationDate(Date.from(Instant.now()))
                .build();

        BookResponseDto bookResponseDto = BookResponseDto.builder()
                .bookId(1L)
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(333L)
                .creationDate(Date.from(Instant.now()))
                .build();

        when(bookService.updateBookStock(bookId, newBookStock)).thenReturn(bookResponseDto);

        ResponseEntity<BookResponse> result = bookController.updateBookStock(bookId, newBookStock);

        Assert.assertEquals(expectedResponse.getBookStock(), Objects.requireNonNull(result.getBody()).getBookStock());
    }
}
