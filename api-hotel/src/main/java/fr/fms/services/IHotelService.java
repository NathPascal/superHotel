package fr.fms.services;

import fr.fms.dto.HotelDTO;
import fr.fms.entities.Hotel;

import java.util.List;

public interface IHotelService
{
    List<HotelDTO> getHotels();

    HotelDTO getHotelById(Long id);

    HotelDTO addHotel(HotelDTO hotelDTO);

    Hotel updateHotel(Long id, HotelDTO hotelDTO);

    void deleteHotel(Long id);
}
