package emt.backend.service.impl;

import emt.backend.model.Country;
import emt.backend.model.dto.CountryDto;
import emt.backend.repository.CountryRepository;
import emt.backend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(this.countryRepository.findById(id).orElseThrow());
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country=new Country(countryDto.getName(),countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country=this.countryRepository.findById(id).orElseThrow();
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void delete(Long id) {
     this.countryRepository.deleteById(id);
    }
}
