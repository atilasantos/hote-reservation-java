package br.com.hotel_reservation.hotel_reservation.ApplicationEntryPoint;

import br.com.hotel_reservation.hotel_reservation.models.Customer;
import br.com.hotel_reservation.hotel_reservation.models.IRoom;
import br.com.hotel_reservation.hotel_reservation.models.Reservation;
import br.com.hotel_reservation.hotel_reservation.resources.HotelResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class MainMenu {

    public static void main(String[] args) {

        final int RESERVE_A_ROOM = 1;
        final int SEE_MY_RESERVATIONS = 2;
        final int CREATE_ACCOUNT = 3;
        final int ADMIN_MENU = 4;
        final int EXIT = 5;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

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
                    if(!HotelResource.isThereARoom()){
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
            Collection<IRoom> availableRooms = HotelResource.findARoom(checkIn, checkOut).isEmpty()?
                    new HashSet<>():
                    HotelResource.findARoom(checkIn, checkOut);
            if (!availableRooms.isEmpty()){
                System.out.println("\nAvailable rooms for your check-in and check-out date:");
                availableRooms.forEach(System.out::println);
            }else{
                availableRooms = !(HotelResource.findARoom(checkIn.plusDays(7), checkOut.plusDays(7)).isEmpty())?
                        HotelResource.findARoom(checkIn, checkOut) :
                        new HashSet<>();
                if(!availableRooms.isEmpty()){
                    plusSevenDays = true;
                    System.out.printf("\nAvailable rooms for recommended dates: check-in: %s check-out: %s",
                            checkIn.plusDays(7).format(formatter),
                            checkOut.plusDays(7).format(formatter)
                    );
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
                    System.out.println("Customer doesn't exists, try create an account via option 3.");
                    return;
                }
                System.out.println("What room number would you like to reserve?");
                roomNumber = sc.next();
            }else if(opt.equalsIgnoreCase("n")){
                System.out.println("Please, select option 3 in the menu before trying to reserve a room.");
                return;
            }
            if(!plusSevenDays){
                HotelResource.bookARoom(emailToBeReserved, HotelResource.getRoom(roomNumber),checkIn,checkOut);
            }else{
                HotelResource.bookARoom(
                        emailToBeReserved,
                        HotelResource.getRoom(roomNumber),
                        checkIn.plusDays(7),
                        checkOut.plusDays(7)
                );
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
