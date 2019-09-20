package com.Angular.Controller;


import com.Angular.Entity.Book;
import com.Angular.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/save")
    public Book saveBook(@RequestBody Book bk)
    {
        System.out.println("Saving the Book");
        Book temp =  this.bookService.save(bk);
        System.out.println(temp.toString());
        return temp;
    }

    @GetMapping("/getAll")
    public List<Book> getAllBooks()
    {
        System.out.println("retrieving all books");
        return this.bookService.findAllBook();
    }

    @GetMapping("/getBook/{id}")
    public Optional<Book> getById(@PathVariable int id)
    {
        return this.bookService.findById(id);
    }

    @DeleteMapping("/getBook/{id}")
    public void deleteByID(@PathVariable Integer id)
    {
        System.out.println("Deleting \n"+ this.bookService.findById(id));
        this.bookService.deleteById(id);
    }
}
