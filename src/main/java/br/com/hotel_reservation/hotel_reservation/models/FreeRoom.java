package br.com.hotel_reservation.hotel_reservation.models;


import br.com.hotel_reservation.hotel_reservation.models.enums.RoomType;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        this.price = 0.;
        this.number = roomNumber;
        this.type = enumeration;
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + number + '\'' +
                ", price=" + price +
                ", enumeration=" + type +
                '}';
    }
}
