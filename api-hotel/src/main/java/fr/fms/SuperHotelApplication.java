package fr.fms;

import fr.fms.entities.Hotel;
import fr.fms.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperHotelApplication implements CommandLineRunner {

	@Autowired
	private HotelRepository hotelRepository;

	public static void main(String[] args) {
		SpringApplication.run(SuperHotelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		hotelRepository.save(new Hotel(null, "Première class", "0622558140", "rue des puces de lit", 0, 12, 40,"https://ac-franchise.com/wp-content/uploads/2017/04/logo-franchise-premiere-classe-1.jpg" ));

	}

}
