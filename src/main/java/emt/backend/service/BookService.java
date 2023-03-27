package emt.backend.service;

import emt.backend.model.Book;
import emt.backend.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book>findAll();
    Optional<Book> findById(Long id);

    Optional<Book> save(BookDto bookDto);
    void delete(Long id);
    Optional<Book> edit(Long id,BookDto bookDto);

    void markAsTaken(Long id,Integer copies);

}
