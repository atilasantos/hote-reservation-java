package ApplicationEntryPoint;

import models.Room;
import models.enums.RoomType;
import resources.AdminResource;

import java.util.*;

public class AdminMenu {
    static Scanner sc = new Scanner(System.in);

    public static void menu(){
        int userOption;

        do {
            System.out.println(AdminMenu.menuOptions());
            while(!sc.hasNextInt()){
                System.out.println("Invalid option, type a number between 1 and 5.");
                sc.next();
                AdminMenu.menuOptions();
            }
            userOption = sc.nextInt();
            switch (userOption){
                case 1:
                    AdminResource.getAllCustomers().forEach(System.out::println);
                    break;
                case 2:
                    AdminResource.getAllRooms().forEach(System.out::println);
                    break;
                case 3:
                    AdminResource.displayAllReservations();
                    break;
                case 4:
                    List<Room> rooms = new ArrayList<>();
                    String option = "y";
                    do {
                        Room room = createRoom();
                        if(room != null){
                            rooms.add(room);
                        }
                        System.out.println("Would you like to add another room? 'y' to yes 'n' to no.");
                        option = sc.next();
                        while(!(option.equalsIgnoreCase("n") || option.equalsIgnoreCase("y"))){
                            System.out.println("Invalid option, type 'y' or 'n'.");
                            option = sc.next();
                        }
                        if(option.equals("n")){
                            option = "n";
                        }else if(option.equals("y")){
                            continue;
                        }
                    }while(option != "n");
                    AdminResource.addRoom(rooms);
                    break;
                case 5:
                    break;

            }
        }while(userOption != 5);

    }

    private static Room createRoom() {
        System.out.println("Enter room number");
        String roomNumber = sc.next();
        Double pricePerNight;
        do {
            System.out.println("Enter price per night");
            while (!sc.hasNextDouble()) {
                System.out.println("Type a valid number, ex: 170,50");
                sc.next();
            }
            pricePerNight = sc.nextDouble();
        }while (pricePerNight.isNaN());

        String roomType;
        do {
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            roomType = sc.next();
        }while (!(roomType.equalsIgnoreCase("1") || roomType.equalsIgnoreCase("2")));
        return new Room(roomNumber,pricePerNight, (roomType.equals("1")? RoomType.SINGLE: RoomType.DOUBLE));
    }

    public static String menuOptions(){
        StringBuilder sb = new StringBuilder();
        sb.append("     >Admin menu<       \n");

        sb.append("**************************\n");
        sb.append("1. See all Customers\n");
        sb.append("2. See all Rooms\n");
        sb.append("3. See all Reservations\n");
        sb.append("4. Add a Room\n");
        sb.append("5. Back to Main Menu\n");
        sb.append("+++++++++++++++++++++++++");
        return sb.toString();
    }

}
