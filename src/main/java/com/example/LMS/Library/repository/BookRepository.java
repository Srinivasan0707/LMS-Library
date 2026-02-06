package com.example.LMS.Library.repository;

import com.example.LMS.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // You can add custom search methods here if needed, e.g.:
    // List<Book> findByTitleContaining(String title);
}