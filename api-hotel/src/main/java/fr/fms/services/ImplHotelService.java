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
        logger.info("Fetching hotel with id: {}", id);
        return hotelRepository.findById(id)
                .map(HotelMapper::toDTO)
                .orElseThrow(() -> {
                    logger.error("Hotel not found with id: {}", id);
                    return new HotelNotFoundException(id);
                });
    }

    @Override
    public HotelDTO addHotel(HotelDTO hotelDTO){
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setPhone(hotelDTO.getPhone());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStars(hotelDTO.getStars());
        hotel.setRooms(hotelDTO.getRooms());
        hotel.setPrice(hotelDTO.getPrice());
        hotel.setImageUrl(hotelDTO.getImageUrl());

        Hotel savedHotel = hotelRepository.save(hotel);
        return HotelMapper.toDTO(savedHotel);
    }
}
