package emt.backend.web;

import emt.backend.model.Book;
import emt.backend.model.dto.BookDto;
import emt.backend.service.AuthorService;
import emt.backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

   @GetMapping("/list")
    public List<Book> showBooks(){
        return this.bookService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book>getBookById(@PathVariable Long id){
        return this.bookService.findById(id).map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());

    }
    @PostMapping("/add")
    public ResponseEntity<Book>createBook(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book>editBook(@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book>deleteBook(@PathVariable Long id){
        this.bookService.delete(id);
                if(this.bookService.findById(id).isEmpty()){
                    return ResponseEntity.ok().build();
                }
                return ResponseEntity.badRequest().build();
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<Book>markAsTakenBook(@PathVariable Long id,@RequestParam Integer copies){
        Book book=bookService.findById(id).orElseThrow();
        if(book==null){
            return ResponseEntity.notFound().build();
        }
        else {
            bookService.markAsTaken(id,copies);
            return ResponseEntity.ok(book);
        }

    }
}








