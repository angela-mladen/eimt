package emt.backend.service;

import emt.backend.model.Country;
import emt.backend.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> edit(Long id,CountryDto countryDto);
    void delete(Long id);

}
