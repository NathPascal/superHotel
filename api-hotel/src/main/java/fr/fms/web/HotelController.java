package fr.fms.web;

import fr.fms.dto.HotelDTO;
import fr.fms.exceptions.HotelNotFoundException;
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
    public HotelController(ImplHotelService implHotelService) {
        this.implHotelService = implHotelService;
    }

    @GetMapping("/hotels")
    public List<HotelDTO> allHotels() {
        return implHotelService.getHotels();
    }

    @GetMapping("/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) throws HotelNotFoundException {
        return implHotelService.getHotelById(id);
    }

    @PostMapping("/hotels")
    public ResponseEntity<HotelDTO> addHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO createdTraining = implHotelService.addHotel(hotelDTO);
        return new ResponseEntity<>(createdTraining, HttpStatus.CREATED);
    }
}