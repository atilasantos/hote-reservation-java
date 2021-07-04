package br.com.hotel_reservation.hotel_reservation.models;

import br.com.hotel_reservation.hotel_reservation.models.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {

    protected String number;
    protected Double price;
    protected RoomType type;

    public Room() {
    }

    public Room(
            @JsonProperty("number") String roomNumber,
            @JsonProperty("price") Double price,
            @JsonProperty("type") RoomType enumeration) {
        this.number = roomNumber;
        this.price = price;
        this.type = enumeration;
    }

    public String getNumber() {
        return this.number;
    }

    public Double getPrice() {
        return this.price;
    }

    public RoomType getType() {
        return this.type;
    }


    @Override
    public String toString() {
        return "Room number: " +
                number +
                " " + (type.equals(RoomType.SINGLE)? "Single bed ": "Double bed ") +
                "Room price: " + String.format("%.2f", price);
    }
}
