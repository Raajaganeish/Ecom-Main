package com.Angular.Service;

import com.Angular.Entity.Book;
import com.Angular.JPA.BookJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookJPA book_jpa;

    @Override
    public Book save(Book bk) {
        return this.book_jpa.save(bk);
    }

    @Override
    public List<Book> findAllBook() {
        return this.book_jpa.findAll();
    }

    @Override
    public Optional<Book> findById(int id) {

        return this.book_jpa.findById(id);

    }

    @Override
    public void deleteById(Integer id) {
        this.book_jpa.deleteById(id);
    }
}
