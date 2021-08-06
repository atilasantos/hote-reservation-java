package br.com.hotel_reservation.hotel_reservation.services;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    RoomRepository roomRepository;

    public void addRoom(Room room){
        roomRepository.save(room);
    }

    public Room getARoom(String roomId){
        return roomRepository.findRoomByNumber(roomId);
    }

    public Collection<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Reservation reserveARoom(Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        return null;
    }

    public Collection<Room> findRooms(LocalDate checkInDate, LocalDate checkOutDate){
        return null;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        return null;
    }

    public Collection<Reservation> getAllReservations(){
        return null;
    }

    public Collection<Room> checkAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate){
        return null;
    }

    public Collection<Room> availableRoomsWithoutReservations(){
        return null;
    }
}
