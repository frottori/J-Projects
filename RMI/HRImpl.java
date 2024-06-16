import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class HRImpl extends UnicastRemoteObject implements HRInterface {

    private ArrayList<Room> rooms;          // List to store rooms
    private ArrayList<Customer> customers; // List to store customers
    private Map<String, ArrayList<HRClientInterface>> waitingClients; // Map to store clients waiting for a room type

    public HRImpl() throws RemoteException {
        super();
        // Initialize the lists
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        waitingClients = new HashMap<>();
        // Add availability of rooms and price
        rooms.add(new Room(40, "A", 75));
        rooms.add(new Room(35, "B", 110));
        rooms.add(new Room(25, "C", 120));
        rooms.add(new Room(30, "D", 150));
        rooms.add(new Room(20, "E", 200));
    }

    public ArrayList<Room> list() throws RemoteException {
        // Return the list of rooms 
        return rooms;
    }

    public double book(String type, int num, String name) throws RemoteException {
        double price = 0;
        for (Room room : rooms) {
            // Find the room with type given
            if (room.getType().equals(type)) {
                // Booking successful
                if (room.getNumber() >= num) {
                    System.out.println("Booking successful for Customer: " + name);
                    // Price of all rooms
                    price = room.getPrice() * num;
                    System.out.println("Total price: " + price);
                    // Number of rooms of this type available now
                    room.setNumber(room.getNumber() - num);
                    System.out.println("Available rooms of type " + room.getType() + ": " + room.getNumber());
                    customers.add(new Customer(name, type, num)); // Add customer to the list of customers
                    return price;
                }
                // No available rooms 
                else if (room.getNumber() == 0) {
                    System.out.println("Booking failed, no available rooms of type " + type);
                    return 0; // No available rooms
                } 
                // Available rooms but not as many as requested
                else {
                    System.out.println("Booking failed, asking for fewer rooms");
                    return -1; // Available rooms but less than what the client wanted
                }
            }
        }
        return price;
    }

    public ArrayList<Customer> guests() throws RemoteException {
         // Return the list of customers
        return customers;
    }

    public ArrayList<Room> cancel(String type, int num, String name) throws RemoteException {
        boolean customerFound = false; // if the customer is in the list 
        for (Customer customer : customers) {
            // If Reservation for is customer found
            if (customer.getName().equals(name) && customer.getType().equals(type)) {
                // If customer has booked more rooms than requested
                if (customer.getNum() > num)
                    customer.setNum(customer.getNum() - num); //change the number of rooms booked
                else if (customer.getNum() == num) 
                    customers.remove(customer);             // remove the customer from the list  
                else 
                {
                    System.out.println("Cancellation failed, cannot cancel more rooms than booked");
                    return new ArrayList<>(); // empty list for unsuccessful cancellation    
                }     
                customerFound = true;
                break;
            }
        }
        // If customer reservation is found
        if (customerFound) {
            System.out.println("Cancellation successful for Customer: " + name);

            // Find the room type
            for (Room room : rooms) {
                if (room.getType().equals(type)) {
                    room.setNumber(room.getNumber() + num); // Increase the number of rooms available
                    // Call notifyClients() to notify clients that their type rooms are available
                    notifyClients(type);
                }
            }
        } 
        // Customer hasn't booked any rooms
        else {
            System.out.println("Cancellation failed, no such booking found for Customer: " + name);
            return new ArrayList<>(); // empty list for unsuccessful cancellation
        }
        return rooms;
    }

    public void registerForCallback(HRClientInterface callbackObj, String type) throws RemoteException {
        // get list of clients with the same type
        ArrayList<HRClientInterface> clients = waitingClients.get(type);
        // if list is empty no client is waiting for this type, so create a new list and add to waitingClients
        if (clients == null) {
            clients = new ArrayList<>();
            waitingClients.put(type, clients);
        }
        clients.add(callbackObj); // add current client to the list
    }

    private void notifyClients(String type) throws RemoteException {
        // get list of clients waiting for this type of room (if any)
        if (waitingClients.containsKey(type)) {
            // Copy to list clients for this type of room
            ArrayList<HRClientInterface> clients = waitingClients.get(type);
            // Notify each client that this type of room is available
            for (HRClientInterface client : clients) {
                client.notifyAvailability(type);
            }  
            // Now all clients have been notifed, this type is available again, so remove it from waitingClients
            waitingClients.remove(type);
        }
    }
}