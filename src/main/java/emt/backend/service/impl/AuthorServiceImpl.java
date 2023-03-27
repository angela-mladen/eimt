package emt.backend.service.impl;

import emt.backend.model.Author;
import emt.backend.model.Country;
import emt.backend.model.dto.AuthorDto;
import emt.backend.repository.AuthorRepository;
import emt.backend.service.AuthorService;
import emt.backend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id).orElseThrow());
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {

        Country country=this.countryService.findById(authorDto.getCountry()).orElseThrow();
        Author author=new Author();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author=authorRepository.findById(id).orElseThrow();
        Country country=this.countryService.findById(authorDto.getCountry()).orElseThrow();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }
}
