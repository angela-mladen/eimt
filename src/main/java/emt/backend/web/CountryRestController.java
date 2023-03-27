package emt.backend.web;

import emt.backend.model.Country;
import emt.backend.model.dto.CountryDto;
import emt.backend.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/country")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping("/list")
    public List<Country> listAllCountries(){
        return this.countryService.listAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country>findCountryById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(country->ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Country>addCountry(@RequestBody CountryDto countryDto){
        return this.countryService.save(countryDto)
                .map(country->ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country>editCountry(@PathVariable Long id,@RequestBody CountryDto countryDto){
        return this.countryService.edit(id,countryDto)
                .map(country->ResponseEntity.ok().body(country))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country>deleteCountry(@PathVariable Long id){
        this.countryService.delete(id);
        if(this.countryService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.badRequest().build();
    }
}
