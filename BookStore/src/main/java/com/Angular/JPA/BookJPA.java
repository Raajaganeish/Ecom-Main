package com.Angular.JPA;

import com.Angular.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPA extends JpaRepository<Book,Integer> {
    

}
