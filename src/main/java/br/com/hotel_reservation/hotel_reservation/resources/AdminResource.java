package br.com.hotel_reservation.hotel_reservation.resources;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.IRoom;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public static Customer getCustomer(String email){
        return null;
    }

    public static void addRoom(List<Room> rooms){
        rooms.forEach(ReservationService::addRoom);
    }

    public static Collection<IRoom> getAllRooms(){
        if(ReservationService.rooms.isEmpty()){
            System.out.println("There is no rooms available.");
        }
        return ReservationService.rooms.values();
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservation();
    }
}
