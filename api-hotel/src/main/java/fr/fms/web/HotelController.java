package fr.fms.web;

import fr.fms.dto.HotelDTO;
import fr.fms.entities.Hotel;
import fr.fms.services.ImplHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class HotelController {

    private final ImplHotelService implHotelService;

    @Autowired
    public HotelController(ImplHotelService implHotelService) {this.implHotelService = implHotelService; }

    @GetMapping("/hotels")
    public List<HotelDTO> getHotels(@RequestParam(value = "cityId", required = false) Long cityId) {
        if (cityId != null) {
            return implHotelService.getHotelsByCityId(cityId);
        } else {
            return implHotelService.getHotels();
        }
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        HotelDTO hotel = implHotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel = implHotelService.addHotel(hotelDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        Hotel updatedHotel = implHotelService.updateHotel(id, hotelDTO);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id){
        implHotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}