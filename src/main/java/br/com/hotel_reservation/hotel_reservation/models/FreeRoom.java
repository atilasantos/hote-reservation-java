package br.com.hotel_reservation.hotel_reservation.models;


import br.com.hotel_reservation.hotel_reservation.models.enums.RoomType;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        this.price = 0.;
        this.roomNumber = roomNumber;
        this.roomType = enumeration;
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumeration=" + roomType +
                '}';
    }
}
