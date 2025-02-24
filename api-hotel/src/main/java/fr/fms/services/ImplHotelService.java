package fr.fms.services;

import fr.fms.dto.HotelDTO;
import fr.fms.entities.Hotel;
import fr.fms.exceptions.HotelNotFoundException;
import fr.fms.mappers.HotelMapper;
import fr.fms.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImplHotelService implements IHotelService{

    private final Logger logger = LoggerFactory.getLogger(ImplHotelService.class);

    private final HotelRepository hotelRepository;

    @Autowired
    public ImplHotelService(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public List<HotelDTO> getHotels() throws RuntimeException{

        return HotelMapper.toDTOList(hotelRepository.findAll());
    }


    public HotelDTO getHotelById(Long id) {
        logger.info("Fetching training with id: {}", id);
        return hotelRepository.findById(id)
                .map(HotelMapper::toDTO)
                .orElseThrow(() -> {
                    logger.error("Training not found with id: {}", id);
                    return new HotelNotFoundException(id);
                });
    }

    @Override
    public HotelDTO addHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setPhone(hotel.getPhone());
        hotel.setAddress(hotel.getAddress());
        hotel.setStars(hotel.getStars());
        hotel.setRooms(hotel.getRooms());
        hotel.setPrice(hotel.getPrice());
        hotel.setImageUrl(hotel.getImageUrl());

       Hotel savedHotel = hotelRepository.save(hotel);
        return HotelMapper.toDTO(savedHotel);
    }
}
