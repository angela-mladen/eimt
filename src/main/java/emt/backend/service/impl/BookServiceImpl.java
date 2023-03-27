package emt.backend.service.impl;

import emt.backend.model.*;
import emt.backend.model.dto.BookDto;
import emt.backend.model.exceptions.InvalidNumberOfCopiesException;
import emt.backend.repository.BookRepository;
import emt.backend.service.AuthorService;
import emt.backend.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id)).orElseThrow();
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Book book=new Book();
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Author author=this.authorService.findById(bookDto.getAuthor()).orElseThrow();
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
      this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Optional<Book> b=bookRepository.findById(id);
        if(b.isEmpty()){
            throw new RuntimeException("book is not found");
        }
        Book book=b.get();
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Optional<Author> auth=this.authorService.findById(bookDto.getAuthor());
        if(auth.isEmpty()){
            throw new RuntimeException("author is not found");
        }
        Author author=auth.get();
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void markAsTaken(Long id,Integer copies) {
        Book book=bookRepository.findById(id).orElseThrow();
        if(copies<book.getAvailableCopies()){
            throw new InvalidNumberOfCopiesException("Number of copies is invalid");
        }
        book.setAvailableCopies(book.getAvailableCopies()-copies);
        this.bookRepository.save(book);

    }
}
