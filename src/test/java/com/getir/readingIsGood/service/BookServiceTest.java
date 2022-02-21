package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.book.BookRequestDto;
import com.getir.readingIsGood.dto.book.BookResponseDto;
import com.getir.readingIsGood.entity.Book;
import com.getir.readingIsGood.exception.MissingBookException;
import com.getir.readingIsGood.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void addNewBook() {

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(100L)
                .build();

        BookResponseDto expectedResult = BookResponseDto.builder()
                .bookId(1L)
                .bookAuthor("bookAuthor")
                .bookName("bookName")
                .bookIsbnNumber("123321")
                .bookPrice(BigDecimal.TEN)
                .bookStock(100L)
                .creationDate(Date.from(Instant.now()))
                .build();

        BookResponseDto actualResult = bookService.addNewBook(bookRequestDto);

        Assert.assertEquals(expectedResult.getBookName(), actualResult.getBookName());
    }

    @Test(expected = MissingBookException.class)
    public void updateBookStockWithBookDoesNotExistException() {

        Long bookId = 1L;
        Long newBookStock = 333L;

        BookResponseDto expectedResult = BookResponseDto.builder()
                .bookId(bookId)
                .bookAuthor("bookAuthor")
                .bookIsbnNumber("123321")
                .bookName("bookName")
                .bookPrice(BigDecimal.TEN)
                .bookStock(newBookStock)
                .creationDate(Date.from(Instant.now()))
                .build();

        BookResponseDto actualResult = bookService.updateBookStock(bookId, newBookStock);

        Assert.assertEquals(expectedResult.getBookStock(), actualResult.getBookStock());
    }

    @Test
    public void updateBookStock() {

        Long bookId = 1L;
        Long newBookStock = 333L;

        BookResponseDto expectedResult = BookResponseDto.builder()
                .bookId(bookId)
                .bookAuthor("bookAuthor")
                .bookIsbnNumber("123321")
                .bookName("bookName")
                .bookPrice(BigDecimal.TEN)
                .bookStock(newBookStock)
                .creationDate(Date.from(Instant.now()))
                .build();

        Book book = new Book();
        book.setBookId(bookId);
        book.setBookAuthor("bookAuthor");
        book.setBookIsbnNumber("123321");
        book.setBookName("bookName");
        book.setBookStock(150L);
        book.setBookPrice(BigDecimal.TEN);
        book.setCreationDate(Date.from(Instant.now()));

        when(bookRepository.findBookByBookId(bookId)).thenReturn(book);
        when(bookRepository.findBookByBookName(any(String.class))).thenReturn(book);

        BookResponseDto actualResult = bookService.updateBookStock(bookId, newBookStock);

        Assert.assertEquals(expectedResult.getBookStock(), actualResult.getBookStock());
    }
}
