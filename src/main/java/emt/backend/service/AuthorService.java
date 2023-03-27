package emt.backend.service;

import emt.backend.model.Author;
import emt.backend.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> edit(Long id,AuthorDto authorDto);
    void delete(Long id);
}
