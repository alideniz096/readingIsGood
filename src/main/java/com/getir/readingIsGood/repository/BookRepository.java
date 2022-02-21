package com.getir.readingIsGood.repository;

import com.getir.readingIsGood.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByBookId(Long bookId);

    Book findBookByBookName(String bookName);

    boolean existsByBookName(String bookName);

    boolean existsByBookIsbnNumber(String bookIsbnNumber);
}
