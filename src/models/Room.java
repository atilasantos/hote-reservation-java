package models;

import models.enums.RoomType;

public class Room implements IRoom{

    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public Room() {
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room number: " +
                roomNumber +
                " " + (enumeration.equals(RoomType.SINGLE)? "Single bed ": "Double bed ") +
                "Room price: " + String.format("%.2f",price);
    }
}
