package br.com.hotel_reservation.hotel_reservation.resources;

import br.com.hotel_reservation.hotel_reservation.exceptions.CustomerNotFoundException;
import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/hotel-reservation/v1/admin")
public class AdminResource {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/room", consumes = "application/json")
    public static void addRoom(@RequestBody Room room){
        ReservationService.addRoom(room);
    }

    @GetMapping("/rooms")
    public ResponseEntity<Collection<Room>> getAllRooms(){
        return ResponseEntity.ok(ReservationService.rooms.values());
    }

    @GetMapping("/customers")
    public static ResponseEntity<Collection<Customer>> getAllCustomers(){
        return ResponseEntity.ok(CustomerService.getAllCustomers());
    }

    @GetMapping("/reservations")
    public static ResponseEntity<Collection<Reservation>> displayAllReservations(){
        return ResponseEntity.ok(ReservationService.getAllReservations());
    }
}
