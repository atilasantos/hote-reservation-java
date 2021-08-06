package br.com.hotel_reservation.hotel_reservation.repositories;

import br.com.hotel_reservation.hotel_reservation.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findRoomByNumber(String number);
}
