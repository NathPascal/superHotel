package fr.fms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "T_hotel")
public class Hotel implements Serializable{

        @Serial
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String phone;
        private String address;
        private int stars;
        private int rooms;
        private double price;
        private String imageUrl;

}
