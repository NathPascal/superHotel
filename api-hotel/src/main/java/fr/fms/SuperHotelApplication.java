package fr.fms;

import ch.qos.logback.core.encoder.EchoEncoder;
import fr.fms.entities.*;
import fr.fms.repository.*;
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
	public void run(String... args) throws Exception{

		userRepository.save(new User(null, "nath", passwordEncoder.encode("12345"), true));

		City paris = new City(null, "Paris", null);
		City toulouse = new City(null, "Toulouse", null);
		cityRepository.saveAll(List.of(paris, toulouse));

		hotelRepository.save(new Hotel(null, "Première classe", "0622558140", "rue des puces de lit", 0, 12, 40,"https://ac-franchise.com/wp-content/uploads/2017/04/logo-franchise-premiere-classe-1.jpg", paris ));
		hotelRepository.save(new Hotel(null, "Hotel Dream","051020101","rue de la mer",4,20,150,"https://i.pinimg.com/originals/20/07/d6/2007d6dd4ca8f4b527d19c7baaefab7e.jpg", toulouse));

		cityRepository.save(new City(null, "Lyon", null));

		System.out.println("Données de test insérées avec succès !");
	}

	public  void initData(){

		User rachid = new User(null, "rachid", passwordEncoder.encode("fms2025"), true);
		userRepository.save(rachid);
		City tarbes = new City(null, "tarbes", null);
		City ibos = new City(null, "Ibos", null);
		Reservation reservation = new Reservation();
		reservation.setUser(rachid);
		reservation.setHotel(new Hotel(null, "Première classe", "0622558140", "rue des puces de lit", 0, 12, 40,"https://ac-franchise.com/wp-content/uploads/2017/04/logo-franchise-premiere-classe-1.jpg", tarbes ));
		reservation.setNombreDePersonne(2);
		reservation.setDateArrive(LocalDateTime.of(2025, 6, 30, 15, 0));
		reservation.setDateDepart(LocalDateTime.of(2025, 7, 3, 11, 0));
		reservationRepository.save(reservation);

		Reservation reservation2 = new Reservation();
		reservation.setUser(rachid);
		reservation.setHotel(new Hotel(null, "Deuxieme classe", "0622558140", "rue des puces de lit", 0, 12, 40,"https://ac-franchise.com/wp-content/uploads/2017/04/logo-franchise-premiere-classe-1.jpg", tarbes ));
		reservation.setNombreDePersonne(2);
		reservation.setDateArrive(LocalDateTime.of(2025, 6, 30, 15, 0));
		reservation.setDateDepart(LocalDateTime.of(2025, 7, 3, 11, 0));
		reservationRepository.save(reservation);

		Avis avis = new Avis();
		avis.setNote(4L);
		avis.setText("Super séjour, personnel accueillant !");
		avis.setReservation(reservation);
		avisRepository.save(avis);

		Avis avis1 = new Avis();
		avis.setNote(4L);
		avis.setText("Séjour agréable, personnel souriant !");
		avis.setReservation(reservation);
		avisRepository.save(avis);

		Avis avis2 = new Avis();
		avis.setNote(3L);
		avis.setText("Séjour plaisant, mais apr contre il manque la climatisation !");
		avis.setReservation(reservation);
		avisRepository.save(avis);

	}

}
