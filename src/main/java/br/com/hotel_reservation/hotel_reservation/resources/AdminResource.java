package br.com.hotel_reservation.hotel_reservation.resources;

import br.com.hotel_reservation.hotel_reservation.exceptions.CustomerNotFoundException;
import br.com.hotel_reservation.hotel_reservation.exceptions.InvalidInputFormatException;
import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.IRoom;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel-reservation/v1/admin")
public class AdminResource {


    @GetMapping("/customer")
    public ResponseEntity<Customer>  getCustomer(@RequestParam String email) throws CustomerNotFoundException {
        return ResponseEntity.ok(CustomerService.getCustomer(email));
    }

    @PostMapping(path = "/room", consumes = "application/json")
    public static void addRoom(@RequestBody Room room){
        ReservationService.addRoom(room);
        ResponseEntity.status(HttpStatus.resolve(201));
    }

    @GetMapping("rooms")
    public ResponseEntity<Collection<IRoom>> getAllRooms(){
        return ResponseEntity.ok(ReservationService.rooms.values());
    }

    @GetMapping("/customers")
    public static ResponseEntity<Collection<Customer>> getAllCustomers(){
        return ResponseEntity.ok(CustomerService.getAllCustomers());
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservation();
    }
}
