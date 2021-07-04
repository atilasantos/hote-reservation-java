package br.com.hotel_reservation.hotel_reservation.models;

import br.com.hotel_reservation.hotel_reservation.models.enums.RoomType;

import java.time.LocalDate;

public class Reservation {

    private Customer customer;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+++++++++++++++++++++++++++++++++++\n");
        sb.append("Reservation\n");
        sb.append(getCustomer().getFirstName() + " " + getCustomer().getLastName() + "\n");
        sb.append("Room: " + getRoom().getNumber() + " - " + (getRoom().getType().equals(RoomType.SINGLE)? "Single bed" : "Double bed\n"));
        sb.append("Price: $" + String.format("%.2f",getRoom().getPrice()) + " price per night\n");
        sb.append("Check-in Date: " + getCheckInDate().getDayOfWeek() + " " + getCheckInDate().getMonth() + " " +
                getCheckInDate().getDayOfMonth() + " " + getCheckInDate().getYear() + "\n");
        sb.append("Check-out Date: " + getCheckOutDate().getDayOfWeek() + " " + getCheckOutDate().getMonth() + " " +
                getCheckOutDate().getDayOfMonth() + " " + getCheckOutDate().getYear() + "\n");
        sb.append("+++++++++++++++++++++++++++++++++++\n");
        return sb.toString();
    }
}
