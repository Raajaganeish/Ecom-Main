package com.Angular.Service;

import com.Angular.Entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(Book bk);

    List<Book> findAllBook();

    Optional<Book> findById(int id);

    void deleteById(Integer id);
}
