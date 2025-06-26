package fr.fms.cucumber;

import fr.fms.dto.HotelDTO;
import fr.fms.entities.City;
import fr.fms.repository.CityRepository;
import fr.fms.services.ImplHotelService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelStepDefinition {

    @Autowired
    private ImplHotelService service;

    @Autowired
    private CityRepository repository;

    private HotelDTO hotelDTO;

    @Given("une ville existe avec un identifiant")
    public void une_ville_existe_avec_identifiant(){
        City city = new City();
        city.setId(1L);
        city.setName("Paris");
        repository.save(city);
    }

    @When("j'ajoute un hotel avec ses informations")
    public void j_ajoute_un_hotel_avec_ses_informations(){
        hotelDTO = new HotelDTO();
        hotelDTO.setName("Toulouse");
        hotelDTO.setPhone("0563555555");
        hotelDTO.setAddress("place du Capitole");
        hotelDTO.setStars(3);
        hotelDTO.setRooms(10);
        hotelDTO.setPrice(100);
        hotelDTO.setImageUrl("image1");
        hotelDTO.setCityId(1L);

        hotelDTO = service.addHotel(hotelDTO);
    }

    @Then("la réponse doit contenir l'hotel avec son nom")
    public void la_reponse_doit_contenir_l_hotel_avec_son_nom() {
        assertEquals("Toulouse", hotelDTO.getName());
    }
}
