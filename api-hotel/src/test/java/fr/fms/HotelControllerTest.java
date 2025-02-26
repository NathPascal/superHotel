package fr.fms;

import fr.fms.dto.HotelDTO;
import fr.fms.exceptions.HotelNotFoundException;
import fr.fms.services.ImplHotelService;
import fr.fms.web.HotelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class HotelControllerTest {

    @Mock
    private ImplHotelService implHotelService;

    @InjectMocks
    private HotelController hotelController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    // Test de la récupération de tous les hôtels
//    @Test
//    public void testGetAllHotels(){
//        HotelDTO hotel1 = new HotelDTO(1L, "Hotel Paris", "0123456789", "rue de Paris", 5, 100, 200.0, "imageUrl1", 1L);
//        HotelDTO hotel2 = new HotelDTO(2L, "Hotel New York", "9876543210", "rue de New York", 4, 150, 300.0, "imageUrl2", 2L);
//
//        when(implHotelService.getHotelsByCityId(1L)).thenReturn(Arrays.asList(hotel1, hotel2));
//
//        List<HotelDTO> hotels = hotelController.getHotels(1L); // Pass the required argument
//
//        assertEquals("Hotel Paris", hotels.get(0).getName());
//        assertEquals(2, hotels.size());
//
//        verify(implHotelService, times(1)).getHotelsByCityId(1L);
//    }

    // Test de récupération d'un hôtel par ID existant
//    @Test
//    public void testGetHotelById_ExistingId(){
//        HotelDTO hotel = new HotelDTO(1L, "Hotel Paris", "0123456789", "rue de Paris", 5, 100, 200.0, "imageUrl1", 1L);
//
//        when(implHotelService.getHotelById(1L)).thenReturn(hotel);
//
//        HotelDTO result = hotelController.getHotelById(1L);
//
//        assertEquals("Hotel Paris", result.getName());
//        verify(implHotelService, times(1)).getHotelById(1L);
//    }

    // Test de la récupération d'un hôtel avec un ID inexistant
    @Test
    public void testGetHotelById_NonExistingId(){
        when(implHotelService.getHotelById(99L)).thenThrow(new HotelNotFoundException(99L));

        assertThrows(HotelNotFoundException.class, () -> hotelController.getHotelById(99L));
        verify(implHotelService, times(1)).getHotelById(99L);
    }
}