package fr.fms.mappers;

import fr.fms.dto.HotelDTO;
import fr.fms.entities.Hotel;

import java.util.List;

public class HotelMapper {

    private HotelMapper() {
        throw new IllegalStateException("Utility class");
    }

    //convertir une entité en DTO
    public static HotelDTO toDTO(Hotel hotel){
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setPhone(hotel.getPhone());
        dto.setAddress(hotel.getAddress());
        dto.setStars(hotel.getStars());
        dto.setRooms(hotel.getRooms());
        dto.setPrice(hotel.getPrice());
        return dto;
    }

    //convertir une liste d'entités en liste de DTOs
    public static List<HotelDTO> toDTOList(List<Hotel> hotels){
        return hotels.stream().map(HotelMapper::toDTO).toList();
    }
}

