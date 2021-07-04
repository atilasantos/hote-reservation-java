package br.com.hotel_reservation.hotel_reservation.services;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.IRoom;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    public static List<Reservation> allReservations = new ArrayList<>();
    public static Map<String, IRoom> rooms = new HashMap<>();

    public static void addRoom(IRoom room){
        if(!rooms.containsKey(room.getRoomNumber())){
            rooms.put(room.getRoomNumber(),room);
        }else{
            System.out.printf("Tried to add a existing room(number: %s)\n",room.getRoomNumber());
        }

    }

    public static IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, LocalDate checkInDate, LocalDate checkOutDate){
        Reservation reservation = new Reservation(customer,room,checkInDate,checkOutDate);
        allReservations.add(reservation);
        System.out.println(reservation);
        return reservation;
    }

    public static Collection<IRoom> findRooms(LocalDate checkInDate, LocalDate checkOutDate){
        Collection<IRoom> roomsAvailable = checkAvailableRooms(checkInDate,checkOutDate);
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

    public static void printAllReservation(){
        if(!allReservations.isEmpty()){
            allReservations.forEach(System.out::println);
        }else{
            System.out.println("There is no reservations available.");
        }
    }

    public static Collection<IRoom> checkAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate){
        Set<IRoom> roomsWithReservations = new HashSet<>();
        Set<IRoom> unavailableRooms = new HashSet<>();

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

    public static Collection<IRoom> availableRoomsWithoutReservations(){
        Map<String, IRoom> copyRooms = new HashMap<>(rooms);
        Set<IRoom> roomsReserved = allReservations.stream()
                    .map(Reservation::getRoom)
                    .collect(Collectors.toSet());
        copyRooms.values().removeAll(roomsReserved);
        return copyRooms.values();
    }
}
