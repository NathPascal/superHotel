package fr.fms;

import ch.qos.logback.core.encoder.EchoEncoder;
import fr.fms.entities.*;
import fr.fms.repository.*;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SuperHotelApplication implements CommandLineRunner {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AvisRepository avisRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public static void main(String[] args) {
		SpringApplication.run(SuperHotelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initData();
	}

	@Transactional
	public void initData() {
		// Création de villes
		City paris = new City("Paris");
		City lyon = new City("Lyon");
		City marseille = new City("Marseille");
		cityRepository.saveAll(List.of(paris, lyon, marseille));

		// Création d'hôtels
		Hotel hotel1 = new Hotel("Hôtel Parisien", "Un hôtel de luxe situé au cœur de Paris.", 4.5, 150.0, "https://example.com/hotel1.jpg", paris);
		Hotel hotel2 = new Hotel("Hôtel Lyonnais", "Un hôtel confortable à Lyon.", 4.0, 120.0, "https://example.com/hotel2.jpg", lyon);
		Hotel hotel3 = new Hotel("Hôtel Marseillais", "Un hôtel avec vue sur la mer à Marseille.", 4.2, 130.0, "https://example.com/hotel3.jpg", marseille);
		hotelRepository.saveAll(List.of(hotel1, hotel2, hotel3));

		// Création d'utilisateurs
		User user1 = new User("user1", passwordEncoder.encode("password1"), "user1@example.com", true);
		User user2 = new User("user2", passwordEncoder.encode("password2"), "user2@example.com", true);
		User user3 = new User("user3", passwordEncoder.encode("password3"), "user3@example.com", true);
		userRepository.saveAll(List.of(user1, user2, user3));

		// Création de réservations
		Reservation reservation1 = new Reservation(user1, hotel1, 2, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
		Reservation reservation2 = new Reservation(user2, hotel2, 1, LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(4));
		Reservation reservation3 = new Reservation(user3, hotel3, 3, LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(5));
		reservationRepository.saveAll(List.of(reservation1, reservation2, reservation3));

		// Création d'avis
		Avis avis1 = new Avis(5, "Excellent séjour, très bon service !", reservation1);
		Avis avis2 = new Avis(4, "Très bon hôtel, mais un peu cher.", reservation2);
		Avis avis3 = new Avis(3, "Correct, mais rien d'exceptionnel.", reservation3);
		avisRepository.saveAll(List.of(avis1, avis2, avis3));
	
		System.out.println("Données de test insérées avec succès !");
	}
}
