package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.book.BookDto;
import com.getir.readingIsGood.dto.book.BookRequestDto;
import com.getir.readingIsGood.dto.book.BookResponseDto;
import com.getir.readingIsGood.entity.Book;
import com.getir.readingIsGood.exception.BookAlreadyExistException;
import com.getir.readingIsGood.exception.MissingBookException;
import com.getir.readingIsGood.mappers.BookMapper;
import com.getir.readingIsGood.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static org.mapstruct.factory.Mappers.getMapper;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper = getMapper(BookMapper.class);
    private Logger logger = LoggerFactory.getLogger(BookService.class);


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto addNewBook(BookRequestDto bookRequestDto) {

        Book newBook = bookMapper.bookRequestDtoToBook(bookRequestDto);

        if (isSameBookExist(newBook)) {
            throw new BookAlreadyExistException("This book is already registered in the system.");
        }

        bookRepository.save(newBook);

        return bookMapper.bookToBookResponseDto(newBook);
    }

    public BookResponseDto updateBookStock(Long bookId, Long newBookStock) {

        Book book = bookRepository.findBookByBookId(bookId);

        BookDto bookDto = bookMapper.bookToBookDto(book);

        if (isBookExist(bookDto)) {

            book.setBookStock(newBookStock);
            bookRepository.save(book);
            return bookMapper.bookToBookResponseDto(book);
        }

        throw new MissingBookException("Book does not exist.");
    }

    private boolean isBookExist(BookDto bookDto) {
        if (bookDto != null) {
            if (bookDto.getBookName() != null) {
                return bookRepository.findBookByBookName(bookDto.getBookName()) != null;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean isSameBookExist(Book book) {
        return bookRepository.existsByBookName(book.getBookName()) || bookRepository.existsByBookIsbnNumber(book.getBookIsbnNumber());
    }
}
