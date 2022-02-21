package com.getir.readingIsGood.mappers;

import com.getir.readingIsGood.controller.vo.book.BookRequest;
import com.getir.readingIsGood.controller.vo.book.BookResponse;
import com.getir.readingIsGood.dto.book.BookDto;
import com.getir.readingIsGood.dto.book.BookRequestDto;
import com.getir.readingIsGood.dto.book.BookResponseDto;
import com.getir.readingIsGood.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDto);

    BookResponseDto bookToBookResponseDto(Book book);

    Book bookRequestDtoToBook(BookRequestDto bookRequestDto);

    BookRequestDto bookRequestToBookRequestDto(BookRequest bookRequest);


    BookResponse bookResponseDtoToBookResponse(BookResponseDto bookResponseDto);



}
