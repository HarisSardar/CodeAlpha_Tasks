package hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {

    // Room class representing a hotel room
    static class Room {
        private int roomNumber;
        private String category;
        private boolean isAvailable;
        private double price;

        public Room(int roomNumber, String category, double price) {
            this.roomNumber = roomNumber;
            this.category = category;
            this.isAvailable = true;
            this.price = price;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getCategory() {
            return category;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public double getPrice() {
            return price;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }
    }

    // Reservation class representing a booking
    static class Reservation {
        private static int counter = 0;
        private int reservationId;
        private Room room;
        private String guestName;
        private boolean isPaid;

        public Reservation(Room room, String guestName) {
            this.reservationId = ++counter;
            this.room = room;
            this.guestName = guestName;
            this.isPaid = false;
            room.setAvailable(false);
        }

        public int getReservationId() {
            return reservationId;
        }

        public Room getRoom() {
            return room;
        }

        public String getGuestName() {
            return guestName;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public void setPaid(boolean paid) {
            isPaid = paid;
        }

        public void cancelReservation() {
            room.setAvailable(true);
        }

        @Override
        public String toString() {
            return "Reservation ID: " + reservationId + "\nGuest: " + guestName + "\nRoom Number: " + room.getRoomNumber() +
                    "\nCategory: " + room.getCategory() + "\nPrice: $" + room.getPrice() + "\nPaid: " + (isPaid ? "Yes" : "No");
        }
    }

    // Hotel class managing rooms and reservations
    static class Hotel {
        private List<Room> rooms;
        private List<Reservation> reservations;

        public Hotel() {
            rooms = new ArrayList<>();
            reservations = new ArrayList<>();
        }

        public void addRoom(Room room) {
            rooms.add(room);
        }

        public void searchRooms(String category) {
            System.out.println("Available rooms in " + category + " category:");
            for (Room room : rooms) {
                if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                    System.out.println("Room Number: " + room.getRoomNumber() + ", Price: $" + room.getPrice());
                }
            }
        }

        public Reservation makeReservation(int roomNumber, String guestName) {
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                    Reservation reservation = new Reservation(room, guestName);
                    reservations.add(reservation);
                    System.out.println("Reservation successful! Reservation ID: " + reservation.getReservationId());
                    return reservation;
                }
            }
            System.out.println("Room not available.");
            return null;
        }

        public void viewReservation(int reservationId) {
            for (Reservation reservation : reservations) {
                if (reservation.getReservationId() == reservationId) {
                    System.out.println(reservation);
                    return;
                }
            }
            System.out.println("Reservation not found.");
        }

        public void processPayment(int reservationId) {
            for (Reservation reservation : reservations) {
                if (reservation.getReservationId() == reservationId) {
                    if (!reservation.isPaid()) {
                        reservation.setPaid(true);
                        System.out.println("Payment processed successfully for Reservation ID: " + reservationId);
                    } else {
                        System.out.println("Payment already made for this reservation.");
                    }
                    return;
                }
            }
            System.out.println("Reservation not found.");
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.addRoom(new Room(101, "Single", 100.0));
        hotel.addRoom(new Room(102, "Double", 150.0));
        hotel.addRoom(new Room(103, "Suite", 300.0));
        hotel.addRoom(new Room(104, "Single", 100.0));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hotel Reservation System");
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservation");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single/Double/Suite): ");
                    String category = scanner.next();
                    hotel.searchRooms(category);
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.next();
                    hotel.makeReservation(roomNumber, guestName);
                    break;
                case 3:
                    System.out.print("Enter reservation ID: ");
                    int reservationId = scanner.nextInt();
                    hotel.viewReservation(reservationId);
                    break;
                case 4:
                    System.out.print("Enter reservation ID: ");
                    reservationId = scanner.nextInt();
                    hotel.processPayment(reservationId);
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
