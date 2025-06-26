package fr.fms.services;


import fr.fms.entities.Avis;
import fr.fms.entities.Reservation;
import fr.fms.repository.AvisRepository;
import fr.fms.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {


        @Autowired
        private ReservationRepository reservationRepository;

        public List<Reservation> getAllReservation(){
            return reservationRepository.findAll();
        }
        public Optional<Reservation> getReservationById(Long id){
            return reservationRepository.findById(id);
        }
        public Reservation saveAvis(Reservation reservation){
            return reservationRepository.save(reservation);
        }
        public void deleteAvis(Long id){
            reservationRepository.deleteById(id);
        }
}
