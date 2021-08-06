package br.com.hotel_reservation.hotel_reservation.resources;

import br.com.hotel_reservation.hotel_reservation.exceptions.CustomerNotFoundException;
import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/hotel-reservation/v1")
public class HotelResource {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/{email}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomer(email));
    }

    @GetMapping("/customer")
    public ResponseEntity<Collection<Customer>> getCustomerByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(customerService.getCustomersByName(name));
    }

    @PostMapping(path = "/customer", consumes = "application/json")
    public ResponseEntity<Customer> createACustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(customer.getId()).
                toUri()).
                body(customer);
    }

    @GetMapping("/room")
    public ResponseEntity<Collection<Room>> findARoom(LocalDate checkIn, LocalDate checkOut){
        return null;
    }
}