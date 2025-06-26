package fr.fms.entities;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "T_Avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long note;
    private String text;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
