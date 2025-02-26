package fr.fms.services;

import fr.fms.dto.HotelDTO;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.exceptions.HotelNotFoundException;
import fr.fms.exceptions.ResourceNotFoundException;
import fr.fms.mappers.HotelMapper;
import fr.fms.repository.CityRepository;
import fr.fms.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private CityRepository cityRepository;

    //Récupère tous les hôtels
    public List<HotelDTO> getHotels() throws RuntimeException{

        return HotelMapper.toDTOList(hotelRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    // Récupère un hôtel via son id
    public HotelDTO getHotelById(Long id) {
        logger.info("Fetching hotel with id: {}", id);
        return hotelRepository.findById(id)
                .map(HotelMapper::toDTO)
                .orElseThrow(() -> {
                    logger.error("Hotel not found with id: {}", id);
                    return new HotelNotFoundException(id);
                });
    }

    //crée un nouvel hôtel
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

        if (hotelDTO.getCityId() != null) {
            City city = cityRepository.findById(hotelDTO.getCityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ville non trouvée"));
            hotel.setCity(city);
        }

        logger.info("City ID received: {}", hotelDTO.getCityId());

        Hotel savedHotel = hotelRepository.save(hotel);
        return HotelMapper.toDTO(savedHotel);
    }

    // Modifie un hôtel existant
    @Override
    public Hotel updateHotel(Long id, HotelDTO hotelDTO) {
        return hotelRepository.findById(id)
                .map(existingHotel -> {
                    existingHotel.setName(hotelDTO.getName());
                    existingHotel.setPhone(hotelDTO.getPhone());
                    existingHotel.setAddress(hotelDTO.getAddress());
                    existingHotel.setStars(hotelDTO.getStars());
                    existingHotel.setRooms(hotelDTO.getRooms());
                    existingHotel.setPrice(hotelDTO.getPrice());
                    existingHotel.setImageUrl(hotelDTO.getImageUrl());

                    if (hotelDTO.getCityId() != null){
                        City city = cityRepository.findById(hotelDTO.getCityId())
                                .orElseThrow(() -> new ResourceNotFoundException("Ville non trouvée"));
                        existingHotel.setCity(city);
                    }
                    return hotelRepository.save(existingHotel);
                })
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    //Supprime un hôtel existant par son ID
    @Override
    public void deleteHotel(Long id){
        if (!hotelRepository.existsById(id)){
            logger.error("Hôtel non trouvé avec l'id: {}", id);
            throw new HotelNotFoundException(id);
        }
        hotelRepository.deleteById(id);
        logger.info("Hôtel supprimé avec l'id: {}", id);
    }
}
