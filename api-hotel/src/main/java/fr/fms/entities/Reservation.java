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
    public Reservation(User user1, Hotel hotel1, int i, LocalDateTime plusDays, LocalDateTime plusDays2) {
        this.user = user1;
        this.hotel = hotel1;
        this.nombreDePersonne = i;
        this.dateArrive = plusDays;
        this.dateDepart = plusDays2;        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "hotel_id")
    private Hotel  hotel;

    private int nombreDePersonne;
    private LocalDateTime dateArrive;
    private LocalDateTime dateDepart;

    @OneToOne @JoinColumn(name = "avis_id")
    private Avis avis;
}
