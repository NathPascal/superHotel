package fr.fms.services;

import fr.fms.entities.City;

import java.util.List;

public interface ICityService {

    List<City> getAllCities();

    City getCityById(Long id);

    City addCity(City city);

    City updateCity(Long id, City city);

    void deleteCity(Long id);
}
