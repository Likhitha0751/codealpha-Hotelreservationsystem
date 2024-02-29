import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomId;
    String category;
    double price;
    boolean isAvailable;

    public Room(int roomId, String category, double price) {
        this.roomId = roomId;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }
}

class Reservation {
    int reservationId;
    String guestName;
    Room reservedRoom;
    String checkInDate;
    String checkOutDate;

    public Reservation(int reservationId, String guestName, Room reservedRoom, String checkInDate, String checkOutDate) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.reservedRoom = reservedRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static int reservationCounter = 1;

    public static void main(String[] args) {
        initializeRooms();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Exiting Hotel Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100.0));
        rooms.add(new Room(102, "Deluxe", 150.0));
        rooms.add(new Room(103, "Suite", 200.0));
    }

    private static void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println("Room ID: " + room.roomId +
                        ", Category: " + room.category +
                        ", Price: $" + room.price);
            }
        }
        System.out.println();
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room ID for reservation: ");
        int roomId = scanner.nextInt();

        Room selectedRoom = findRoomById(roomId);

        if (selectedRoom != null && selectedRoom.isAvailable) {
            System.out.print("Enter check-in date (YYYY-MM-DD): ");
            String checkInDate = scanner.next();

            System.out.print("Enter check-out date (YYYY-MM-DD): ");
            String checkOutDate = scanner.next();

            Reservation reservation = new Reservation(reservationCounter++, guestName, selectedRoom, checkInDate, checkOutDate);
            reservations.add(reservation);

            selectedRoom.isAvailable = false;

            System.out.println("Reservation successful!\n");
        } else {
            System.out.println("Invalid room ID or the room is not available. Please try again.\n");
        }
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter your name to view booking details: ");
        String guestName = scanner.nextLine();

        System.out.println("Booking Details for " + guestName + ":");
        for (Reservation reservation : reservations) {
            if (reservation.guestName.equals(guestName)) {
                System.out.println("Reservation ID: " + reservation.reservationId);
                System.out.println("Room: " + reservation.reservedRoom.roomId +
                        ", Category: " + reservation.reservedRoom.category +
                        ", Price: $" + reservation.reservedRoom.price);
                System.out.println("Check-in Date: " + reservation.checkInDate);
                System.out.println("Check-out Date: " + reservation.checkOutDate);
                System.out.println();
            }
        }
    }

    private static Room findRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.roomId == roomId) {
                return room;
            }
        }
        return null;
    }
}
