package resources;

import models.Customer;
import models.IRoom;
import models.Reservation;
import models.Room;
import services.CustomerService;
import services.ReservationService;

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
