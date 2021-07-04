package br.com.hotel_reservation.hotel_reservation.config;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.Room;
import br.com.hotel_reservation.hotel_reservation.models.enums.RoomType;
import br.com.hotel_reservation.hotel_reservation.services.CustomerService;
import br.com.hotel_reservation.hotel_reservation.services.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception{

        Customer c1 = new Customer("Bia","Costa", "bia@gmail.com");
        Customer c2 = new Customer("Anna","Beatriz", "anna@gmail.com");
        Customer c3 = new Customer("Julia","Costa", "juju@gmail.com");
        Customer c4 = new Customer("Gustavo","Romao", "gu@gmail.com");
        Customer c5 = new Customer("Fernanda","Suckow", "fernanda@gmail.com");
        Customer c6 = new Customer("Camila","Clara", "camila@gmail.com");
        Customer c7 = new Customer("Atila","Romao", "atila@gmail.com");

        CustomerService.customers.putAll(new HashMap<>(
                Map.of(
                        c1.getEmail(),c1,
                        c2.getEmail(),c2,
                        c3.getEmail(),c3,
                        c4.getEmail(),c4,
                        c5.getEmail(),c5,
                        c6.getEmail(),c6,
                        c7.getEmail(),c7
                )
        )
        );

        Room r1 = new Room("101",750.00, RoomType.DOUBLE);
        Room r2 = new Room("201",110.00, RoomType.SINGLE);
        Room r3 = new Room("301",850.00, RoomType.SINGLE);
        Room r4 = new Room("401",350.00, RoomType.DOUBLE);
        Room r5 = new Room("501",2750.00, RoomType.SINGLE);
        Room r6 = new Room("601",1750.00, RoomType.DOUBLE);

        ReservationService.rooms.putAll(new HashMap<>(
                Map.of(
                        r1.getNumber(),r1,
                        r2.getNumber(),r2,
                        r3.getNumber(),r3,
                        r4.getNumber(),r4,
                        r5.getNumber(),r5,
                        r6.getNumber(),r6
                )
        )
        );
    }
}
