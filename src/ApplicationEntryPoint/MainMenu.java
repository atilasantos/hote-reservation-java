package ApplicationEntryPoint;

import exceptions.InvalidInputFormatException;
import models.Customer;
import models.IRoom;
import models.Reservation;
import models.Room;
import models.enums.RoomType;
import resources.HotelResource;
import services.CustomerService;
import services.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class MainMenu {

    public static void main(String[] args) throws InvalidInputFormatException {

        final int RESERVE_A_ROOM = 1;
        final int SEE_MY_RESERVATIONS = 2;
        final int CREATE_ACCOUNT = 3;
        final int ADMIN_MENU = 4;
        final int EXIT = 5;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String os = System.getProperty("os.name");

//        Customer customer = new Customer("Atila","Romao", "atila.romao@hotmail.com");
//        Customer customer1 = new Customer("Romao","Santos", "romao@hotmail.com");
//        Customer customer2 = new Customer("Carla","Romao", "carla.romao@hotmail.com");
//        Customer customer3 = new Customer("Pedro","Romao", "pedro.romao@hotmail.com");
//        Customer customer4 = new Customer("Joanna","Romao", "joanna.romao@hotmail.com");
//        Room room = new Room("101",1200., RoomType.DOUBLE);
//        Room room2 = new Room("102",650., RoomType.SINGLE);
//        Room room3 = new Room("103",1000., RoomType.SINGLE);
//        ReservationService.addRoom(room);
//        ReservationService.addRoom(room2);
//        ReservationService.addRoom(room3);
//        System.out.println(ReservationService.rooms);
//        System.out.println(ReservationService.rooms.values());
//        ReservationService.rooms.values().removeAll(Arrays.asList(room2));
//        System.out.println(ReservationService.rooms);
//        System.out.println(ReservationService.rooms.values());
//        Reservation reservation = new Reservation(customer,room,LocalDate.now().minusDays(2),LocalDate.now());
//        Reservation reservation1 = new Reservation(customer2,room2,LocalDate.now().plusDays(3),LocalDate.now().plusDays(7));
//        Reservation reservation2 = new Reservation(customer3,room,LocalDate.now().plusDays(30),LocalDate.now().plusDays(40));
//        Reservation reservation3 = new Reservation(customer4,room,LocalDate.now().plusDays(50),LocalDate.now().plusDays(60));
//        Reservation reservation4 = new Reservation(customer1,room,LocalDate.now().plusDays(100),LocalDate.now().plusDays(110));
//        ReservationService.allReservations.addAll(Arrays.asList(reservation,reservation1,reservation2,reservation3,reservation4));
//
//        System.out.println("\n\n+++++++++++++++++++++++++++++++");
//        System.out.println("All rooms:");
//        System.out.println(ReservationService.rooms);
//        System.out.println("All rooms with reservations:");
//        System.out.println(ReservationService.allReservations.stream()
//        .map(Reservation::getRoom)
//        .collect(Collectors.toSet()));
//        System.out.println("All rooms without reservations:");
//        Map<String,IRoom> copyRooms = new HashMap<>(ReservationService.rooms);
//        System.out.println(copyRooms.values().removeAll(ReservationService.allReservations.stream().map(Reservation::getRoom).collect(Collectors.toSet())));
//        System.out.println(ReservationService.rooms.values());
//        System.out.println("+++++++++++++++++++++++++++++++\n\n");
//
//        ReservationService.allReservations.forEach(System.out::println);
//        System.out.println(ReservationService.checkAvailableRooms(LocalDate.parse("06/18/2021",formatter),LocalDate.parse("06/19/2021",formatter)));

        int userOption;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(MainMenu.customerMenu());
            while (!sc.hasNextInt()){
                System.out.println("Invalid option, type a number between 1 and 5.");
                sc.next();
                System.out.println(MainMenu.customerMenu());
            }
            userOption = sc.nextInt();
            switch (userOption){
                case RESERVE_A_ROOM:
                    if(!ReservationService.rooms.isEmpty()){
                        createReservation(sc,formatter);
                    }else{
                        System.out.println("Please, ask Admin to register some rooms.");
                    }
                    break;
                case SEE_MY_RESERVATIONS:
                    try{
                        System.out.print("Type your e-mail: ");
                        String email = sc.next();
                        Collection<Reservation> totalReservations = HotelResource.getCustomersReservations(email);
                        if(!totalReservations.isEmpty()){
                            HotelResource.getCustomersReservations(email).forEach(System.out::println);
                        }else{
                            System.out.println("There is no reservation available for the given e-mail.");
                        }
                    }catch (NullPointerException e){
                        System.out.println("Customer not found.");
                    }
                    break;
                case CREATE_ACCOUNT:
                    MainMenu.mainCreateAccount(sc);
                    break;
                case ADMIN_MENU:
                    AdminMenu.menu();
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("Invalid option, type a number between 1 and 5.");
                    MainMenu.customerMenu();
            }
        }while (userOption != EXIT);

    }

    private static void createReservation(Scanner sc, DateTimeFormatter formatter) {
        String emailToBeReserved = null;
        String roomNumber = null;
        LocalDate checkIn;
        LocalDate checkOut;
        boolean plusSevenDays = false;
        try{
            do {
                System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                String strCheckIn = sc.next();
                checkIn = LocalDate.parse(strCheckIn, formatter);
                System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/21/2020");
                String strCheckOut = sc.next();
                checkOut = LocalDate.parse(strCheckOut,formatter);
                if(checkIn.isAfter(checkOut)) {
                    System.out.println("\n<><><><><><><><><><><><><><><>");
                    System.out.println("Check-in must be before check-out.");
                    System.out.println("<><><><><><><><><><><><><><><>\n");
                }
            }while (checkIn.isAfter(checkOut));
            Collection<IRoom> availableRooms = !(HotelResource.findARoom(checkIn, checkOut).isEmpty())?
                    HotelResource.findARoom(checkIn, checkOut) :
                    new HashSet<>();
            if (!availableRooms.isEmpty()){
                System.out.println("\nAvailable rooms for your check-in and check-out date:");
                availableRooms.forEach(System.out::println);
            }else{
                availableRooms = !(HotelResource.findARoom(checkIn.plusDays(7), checkOut.plusDays(7)).isEmpty())?
                        HotelResource.findARoom(checkIn, checkOut) :
                        new HashSet<>();
                if(!availableRooms.isEmpty()){
                    plusSevenDays = true;
                    System.out.println("\nAvailable rooms for recommended dates:");
                    availableRooms.forEach(System.out::println);
                }else{
                    System.out.println("Theres no room to be reserved or even recommended within a plus 7 days range.");
                }
            }

            System.out.println("Do you have an account with us?");
            String opt = sc.next();
            while(!(opt.equalsIgnoreCase("y") || opt.equalsIgnoreCase("n"))){
                System.out.println("Enter 'y' to yes or 'n' to no.");
                opt = sc.next();
            }
            if(opt.equalsIgnoreCase("y")){
                System.out.println("Enter Email format: name@domain.com");
                emailToBeReserved = sc.next();
                Customer customer = HotelResource.getCustomer(emailToBeReserved);
                if(customer == null){
                    System.out.println("Customer doesn't exists, try create ann account via option 3.");
                    return;
                }
                System.out.println("What room number would you like to reserve?");
                roomNumber = sc.next();
            }else if(opt.equalsIgnoreCase("n")){
                System.out.println("Please, select option 3 in the menu before trying to reserve a room.");
                return;
            }
            if(!plusSevenDays){
                ReservationService.reserveARoom(CustomerService.customers.get(emailToBeReserved), ReservationService.getARoom(roomNumber),checkIn,checkOut);
            }else{
                ReservationService.reserveARoom(CustomerService.customers.get(emailToBeReserved), ReservationService.getARoom(roomNumber),checkIn.plusDays(7),checkOut.plusDays(7));
            }
        }
        catch (DateTimeParseException e){
            System.out.println("Invalid date input.");
        }
    }

    public static String customerMenu(){
        StringBuilder sb = new StringBuilder();
        sb.append("     >Customer menu<       \n");

        sb.append("**************************\n");
        sb.append("1. Find and reserve a room\n");
        sb.append("2. See my reservations\n");
        sb.append("3. Create an account\n");
        sb.append("4. Admin\n");
        sb.append("5. Exit\n");
        sb.append("**************************");
        return sb.toString();
    }

    public static void mainCreateAccount(Scanner sc){
        System.out.print("First name: ");
        String firstName = sc.next();
        System.out.print("Last name: ");
        String lastName = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        HotelResource.createACustomer(email,firstName,lastName);
    }
}
