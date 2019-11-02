package com.bookstore.repository;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.SimpleBookDto;
import com.bookstore.dto.VirtualBookDto;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // avoid
    List<BookDto> findBy();

    // avoid
    @Query("SELECT b.title AS title, a AS author "
            + "FROM Book b LEFT JOIN b.author a")
    List<BookDto> findByViaQuery();

    // avoid
    @Query("SELECT b.title AS title, a.name AS name, a.genre AS genre "
            + "FROM Book b LEFT JOIN b.author a")
    List<SimpleBookDto> findByViaQuerySimpleDto();
    
    // prefer
    @Query("SELECT b.title AS title, a.name AS name, a.genre AS genre "
            + "FROM Book b LEFT JOIN b.author a")
    List<VirtualBookDto> findByViaQueryVirtualDto();
}