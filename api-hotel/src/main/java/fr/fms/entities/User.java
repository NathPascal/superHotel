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
@Table(name = "T_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    boolean active;

}
