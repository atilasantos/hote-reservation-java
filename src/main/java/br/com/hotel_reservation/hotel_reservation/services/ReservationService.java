package br.com.hotel_reservation.hotel_reservation.services;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ReservationService {

    public static List<Reservation> allReservations = new ArrayList<>();
    public static Map<String, Room> rooms = new HashMap<>();

    public static void addRoom(Room room){
        if(!rooms.containsKey(room.getNumber())){
            rooms.put(room.getNumber(),room);
        }else{
            System.out.printf("Tried to add a existing room(number: %s)\n",room.getNumber());
        }

    }

    public static Room getARoom(String roomId){
        return rooms.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        allReservations.add(reservation);
        System.out.println(reservation);
        return reservation;
    }

    public static Collection<Room> findRooms(LocalDate checkInDate, LocalDate checkOutDate){
        Collection<Room> roomsAvailable = checkAvailableRooms(checkInDate,checkOutDate);
        return roomsAvailable;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        if(allReservations.isEmpty()){
            System.out.println("There is no reservation for " + customer.getEmail());
            return null;
        }else{
            return allReservations.stream().filter(reservation -> reservation.getCustomer().getEmail() == customer.getEmail())
                    .collect(Collectors.toSet());
        }
    }

    public static Collection<Reservation> getAllReservations(){
        return allReservations;
    }

    public static Collection<Room> checkAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate){
        Set<Room> roomsWithReservations = new HashSet<>();
        Set<Room> unavailableRooms = new HashSet<>();

        try{
            for (Reservation reservation : allReservations){
                if((reservation.getCheckInDate().isAfter(checkInDate) && reservation.getCheckInDate().isBefore(checkOutDate))
                        || (reservation.getCheckOutDate().isBefore(checkOutDate) && reservation.getCheckOutDate().isAfter(checkInDate))
                        || (reservation.getCheckInDate().isAfter(checkInDate) && reservation.getCheckOutDate().isAfter(checkOutDate))){
                    unavailableRooms.add(reservation.getRoom());
                }else{
                    roomsWithReservations.add(reservation.getRoom());
                }
            }
            roomsWithReservations.removeAll(unavailableRooms);
            roomsWithReservations.addAll(availableRoomsWithoutReservations());
        }catch (NullPointerException e){
            System.out.println("There is no rooms available.");
        }
        return roomsWithReservations;
    }

    public static Collection<Room> availableRoomsWithoutReservations(){
        Map<String, Room> copyRooms = new HashMap<>(rooms);
        Set<Room> roomsReserved = allReservations.stream()
                    .map(Reservation::getRoom)
                    .collect(Collectors.toSet());
        copyRooms.values().removeAll(roomsReserved);
        return copyRooms.values();
    }
}
