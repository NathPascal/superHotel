package fr.fms.services;

import fr.fms.dto.HotelDTO;

import java.util.List;

public interface IHotelService
{
    List<HotelDTO> getHotels();

    HotelDTO getHotelById(Long id);

    HotelDTO addHotel(HotelDTO hotelDTO);
}
