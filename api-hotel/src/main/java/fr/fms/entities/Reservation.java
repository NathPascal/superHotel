package fr.fms.entities;


import fr.fms.repository.HotelRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "t_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private User user;
    private Hotel  hotel;
    private int nombreDePersonne;
    private LocalDateTime dateArrive;
    private LocalDateTime dateDepart;
    @OneToOne
    @JoinColumn(name = "avis_id")
    private Avis avis;
}
