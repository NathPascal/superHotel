package fr.fms;

import ch.qos.logback.core.encoder.EchoEncoder;
import fr.fms.entities.City;
import fr.fms.entities.Hotel;
import fr.fms.entities.User;
import fr.fms.repository.CityRepository;
import fr.fms.repository.HotelRepository;
import fr.fms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SuperHotelApplication implements CommandLineRunner {

//	@Autowired
//	private HotelRepository hotelRepository;
//
//	@Autowired
//	private CityRepository cityRepository;

	//@Autowired
	//private UserRepository userRepository;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SuperHotelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

//		userRepository.save(new User(null, "nath", passwordEncoder.encode("12345"), true));
//
//		City paris = new City(null, "Paris", null);
//		City toulouse = new City(null, "Toulouse", null);
//		cityRepository.saveAll(List.of(paris, toulouse));
//
//		hotelRepository.save(new Hotel(null, "Première classe", "0622558140", "rue des puces de lit", 0, 12, 40,"https://ac-franchise.com/wp-content/uploads/2017/04/logo-franchise-premiere-classe-1.jpg", paris ));
//		hotelRepository.save(new Hotel(null, "Hotel Dream","051020101","rue de la mer",4,20,150,"https://i.pinimg.com/originals/20/07/d6/2007d6dd4ca8f4b527d19c7baaefab7e.jpg", toulouse));
//
//		cityRepository.save(new City(null, "Lyon", null));
//
//		System.out.println("Données de test insérées avec succès !");
	}

}
