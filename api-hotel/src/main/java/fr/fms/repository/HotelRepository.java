package fr.fms.repository;

import fr.fms.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository <Hotel, Long> {
    List<Hotel> findByCityId(Long cityId);
}
