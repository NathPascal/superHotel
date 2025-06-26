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

    public Avis(long l, String string, Reservation reservation1) {
        this.note = l;
        this.text = string;
        this.reservation = reservation1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long note;
    private String text;

    @OneToOne(mappedBy = "avis")
    private Reservation reservation;
}
