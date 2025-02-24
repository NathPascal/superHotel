package fr.fms.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private int stars;
    private int rooms;
    private double price;
    private String imageUrl;

}