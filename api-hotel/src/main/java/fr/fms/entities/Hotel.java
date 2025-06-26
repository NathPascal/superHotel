package fr.fms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

        public Hotel(String string, String string2, double d, double e, String string3, City paris) {
                this.name = string;
                this.address = string2;
                this.price = d;
                this.stars = (int) e;
                this.imageUrl = string3;
                this.city = paris;
        }

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

        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "city_id")
        @JsonBackReference
        private City city;

}
