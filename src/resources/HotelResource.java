package resources;

import exceptions.CustomerNotFoundException;
import exceptions.InvalidInputFormatException;
import models.Customer;
import models.IRoom;
import models.Reservation;
import services.CustomerService;
import services.ReservationService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public static Customer getCustomer(String email){
        try{
            return CustomerService.getCustomer(email);
        }catch (CustomerNotFoundException e){
            System.out.println("Customer doesn't exists.");
        }
        return null;
    }

    public static boolean isThereARoom(){
        return ReservationService.rooms.isEmpty();
    }

    public static void createACustomer(String email, String fistName, String lastName) {
        CustomerService.addCustomer(email,fistName,lastName);
    }

    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        return ReservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(LocalDate checkIn, LocalDate checkOut){
        return ReservationService.findRooms(checkIn,checkOut);
    }
}