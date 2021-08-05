package br.com.hotel_reservation.hotel_reservation.config;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.models.enums.RoomType;
import br.com.hotel_reservation.hotel_reservation.repositories.CustomerRepository;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception{

        Customer c1 = new Customer(null,"Bia","Costa", "bia@gmail.com");
        Customer c2 = new Customer(null,"Anna","Beatriz", "anna@gmail.com");
        Customer c3 = new Customer(null,"Julia","Costa", "juju@gmail.com");
        Customer c4 = new Customer(null,"Gustavo","Romao", "gu@gmail.com");
        Customer c5 = new Customer(null,"Fernanda","Suckow", "fernanda@gmail.com");
        Customer c6 = new Customer(null,"Camila","Clara", "camila@gmail.com");
        Customer c7 = new Customer(null,"Atila","Romao", "atila@gmail.com");

        customerRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));


    }
}
