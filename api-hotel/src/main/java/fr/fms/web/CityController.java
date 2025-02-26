package fr.fms.web;


import fr.fms.entities.City;
import fr.fms.services.ImplCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CityController {

    private final ImplCityService implCityService;

    @Autowired
    public CityController(ImplCityService implCityService) {this.implCityService = implCityService;}

    @GetMapping("/cities")
    public List<City> getAllCities(){
        return implCityService.getAllCities();
    }

    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable Long id) {
        return implCityService.getCityById(id);
    }

    @PostMapping("/cities")
    public City addCity(@RequestBody City city) {
        return implCityService.addCity(city);
    }

    @PutMapping("/cities/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City city) {
        return implCityService.updateCity(id, city);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id){
        implCityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
