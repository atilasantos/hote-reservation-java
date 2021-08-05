package br.com.hotel_reservation.hotel_reservation.repositories;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
}
