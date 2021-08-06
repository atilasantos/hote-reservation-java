package br.com.hotel_reservation.hotel_reservation.repositories;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByEmail(String email);

    Collection<Customer> findCustomerByFirstNameContaining(String name);
}
