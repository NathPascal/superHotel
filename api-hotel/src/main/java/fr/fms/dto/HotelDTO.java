package fr.fms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("cityId")
    private Long cityId;

}