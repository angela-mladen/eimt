package emt.backend.config;

import emt.backend.model.Author;
import emt.backend.model.Book;
import emt.backend.model.Category;
import emt.backend.model.Country;
import emt.backend.repository.AuthorRepository;
import emt.backend.repository.BookRepository;
import emt.backend.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){
        if(authorRepository.count()!=0 || bookRepository.count()!=0 || countryRepository.count()!=0){
            return;
        }
        for(int i=1;i<16;i++){
            create(i);
        }
    }
    private void create(int i){
        Country country=new Country();
        country.setName(String.format("Country %d",i));
        country.setContinent(String.format("Continent %d",i));
        countryRepository.save(country);

        Author author=new Author();
        author.setName(String.format("Author name: %d",i));
        author.setSurname(String.format("Author surname: %d",i));
        author.setCountry(country);
        authorRepository.save(author);

        Book book=new Book();
        book.setName(String.format("Book %d",i));
        book.setCategory(Category.values()[i % Category.values().length]);
        book.setAuthor(author);
        book.setAvailableCopies(i);
        bookRepository.save(book);
    }
}
