package com.github.andre10dias.repository;

import com.github.andre10dias.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
