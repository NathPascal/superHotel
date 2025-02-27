package fr.fms.services;

import fr.fms.entities.City;
import fr.fms.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplCityService implements ICityService {

    private final CityRepository cityRepository;

    @Autowired
    public ImplCityService(CityRepository cityRepository) {this.cityRepository = cityRepository;}

    public List<City> getAllCities(){
        return cityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City city){
        return  cityRepository.findById(id).map(existingCity ->{
            existingCity.setName(city.getName());
            return  cityRepository.save(existingCity);
        }).orElse(null);
    }

    public void deleteCity(Long id){
        cityRepository.deleteById(id);
    }

}
