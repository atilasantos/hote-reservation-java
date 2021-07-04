package br.com.hotel_reservation.hotel_reservation.resources;

import br.com.hotel_reservation.hotel_reservation.exceptions.CustomerNotFoundException;
import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(value = "/hotel-reservation/v1")
public class HotelResource {

    @GetMapping("/customers")
    public static ResponseEntity<Customer> getCustomer(@RequestParam String email) throws CustomerNotFoundException {
        return ResponseEntity.ok(CustomerService.getCustomer(email));
    }

    @PostMapping(path = "/customers", consumes = "application/json")
    public static void createACustomer(@RequestBody Customer customer) {
        CustomerService.addCustomer(customer.getEmail(),customer.getFirstName(),customer.getLastName());
    }

    public static ResponseEntity<Reservation> bookARoom(String customerEmail, Room room, LocalDate checkInDate, LocalDate checkOutDate) throws CustomerNotFoundException {
        return ResponseEntity.ok(ReservationService.reserveARoom(CustomerService.getCustomer(customerEmail),room,checkInDate,checkOutDate));
    }

    @GetMapping("/customers/reservation")
    public static ResponseEntity<Collection<Reservation>> getCustomersReservations(String customerEmail) throws CustomerNotFoundException {
        return ResponseEntity.ok(ReservationService.getCustomersReservation(CustomerService.getCustomer(customerEmail)));
    }

    @GetMapping("room")
    public static Collection<Room> findARoom(LocalDate checkIn, LocalDate checkOut){
        return ReservationService.findRooms(checkIn,checkOut);
    }
}