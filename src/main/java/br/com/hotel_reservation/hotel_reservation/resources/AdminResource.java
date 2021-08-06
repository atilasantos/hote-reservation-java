package br.com.hotel_reservation.hotel_reservation.resources;

import br.com.hotel_reservation.hotel_reservation.exceptions.CustomerNotFoundException;
import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/hotel-reservation/v1/admin")
public class AdminResource {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/room", consumes = "application/json")
    public void addRoom(@RequestBody Room room){
        reservationService.addRoom(room);
    }

    @GetMapping("/rooms")
    public ResponseEntity<Collection<Room>> getAllRooms(){
        return ResponseEntity.ok().body(reservationService.getAllRooms());
    }

    @GetMapping("/customers")
    public ResponseEntity<Collection<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/reservations")
    public  ResponseEntity<Collection<Reservation>> displayAllReservations(){
        return null;
    }
}
