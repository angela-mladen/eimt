package emt.backend.web;

import emt.backend.model.Author;
import emt.backend.model.dto.AuthorDto;
import emt.backend.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/list")
    public List<Author>listAllAuthors(){
        return this.authorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author>findAuthorById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author->ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/create")
    public ResponseEntity<Author>createAuthor(@RequestBody AuthorDto authorDto){
        return this.authorService.save(authorDto)
                .map(author->ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Author>editAuthor(@PathVariable Long id,@RequestBody AuthorDto authorDto){
        return this.authorService.edit(id,authorDto)
                .map(author->ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author>deleteAuthor(@PathVariable Long id){
        this.authorService.delete(id);
        if(this.authorService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
