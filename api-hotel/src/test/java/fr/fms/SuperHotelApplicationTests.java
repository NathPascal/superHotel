package fr.fms;

import fr.fms.dto.HotelDTO;
import fr.fms.services.ImplHotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SuperHotelApplicationTests {

	@Autowired
	private ImplHotelService implHotelService;

	@Test
	void contextLoads() {

		//Récupérer tous les hôtels via le service
		List<HotelDTO> hotels = implHotelService.getHotels();

		// Vérifier que la liste n'est pas nulle et contient les hôtels attendus
		assertThat(hotels).isNotNull();
		List<String> hotelNames = hotels.stream()
						.map(HotelDTO::getName)
								.collect(Collectors.toList());
		assertThat(hotelNames).contains("Première classe", "Hotel Dream", "pullman Hotel");

		// Afficher les hôtels dans la console
		System.out.println("Liste des hôtels :");
		hotels.forEach(System.out::println);
	}

}
