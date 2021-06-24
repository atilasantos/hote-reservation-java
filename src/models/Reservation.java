package models;

import models.enums.RoomType;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
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
        sb.append("Room: " + getRoom().getRoomNumber() + " - " + (getRoom().getRoomType().equals(RoomType.SINGLE)? "Single bed" : "Double bed\n"));
        sb.append("Price: $" + String.format("%.2f",getRoom().getRoomPrice()) + " price per night\n");
        sb.append("Check-in Date: " + getCheckInDate().getDayOfWeek() + " " + getCheckInDate().getMonth() + " " +
                getCheckInDate().getDayOfMonth() + " " + getCheckInDate().getYear() + "\n");
        sb.append("Check-out Date: " + getCheckOutDate().getDayOfWeek() + " " + getCheckOutDate().getMonth() + " " +
                getCheckOutDate().getDayOfMonth() + " " + getCheckOutDate().getYear() + "\n");
        sb.append("+++++++++++++++++++++++++++++++++++\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return room.equals(that.room) && checkInDate.equals(that.checkInDate) && checkOutDate.equals(that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, checkInDate, checkOutDate);
    }
}
