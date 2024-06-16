import java.util.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;

public class HRClient extends UnicastRemoteObject implements HRClientInterface {

    private static final Object lock = new Object(); // lock to critical sections of main thread
    private static boolean waitFlag; // flag to check if main thread should wait or not

    protected HRClient() throws RemoteException {
        super();
    }

    @Override
    public void notifyAvailability(String type) throws RemoteException {
        System.out.println("----Rooms of type " + type + " are now available.----");
        synchronized (lock) {
            waitFlag = true; // Set flag to true to stop waiting
            lock.notifyAll(); // Wake the waiting main thread
        }
    }

    public static void main(String[] args) {
        try {

            // Check if user has given operation and localhost atleast
            if(args.length < 2) printArgsFailed();

            HRInterface c = (HRInterface) Naming.lookup("//" + args[1] + "/HR");
            waitFlag = true; // Initialize flag to true (not to wait)
            
            // Here we call each operation and check if for each the correct args are given
            if (args[0].equals("list") && args.length == 2) {
                listCall(c);
            } else if (args[0].equals("book") && args.length == 5) {
                bookCall(args, c);
            } else if (args[0].equals("guests") && args.length == 2) {
                guestsCall(c);   
            } else if (args[0].equals("cancel") && args.length == 5) {
               cancelCall(args, c);
            }
            else{
               printArgsFailed();
            }

            // Wait for notification
            synchronized (lock) { // critical section for main thread
            // loop stops when flag is set to true and then exits critical section
                while (!waitFlag) { 
                    try {
                        lock.wait(); // Main thread waits indefinitely until notified() for lock object
                        // wait() releases the lock, so other threads can access lock
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); //main thread is interrupted
                    }
                }
            }
            System.exit(0);
        } catch (MalformedURLException murle) {
            System.out.println("\nMalformedURLException");
            System.out.println(murle);
        } catch (RemoteException re) {
            System.out.println("\nRemoteException");
            System.out.println(re);
        } catch (NotBoundException nbe) {
            System.out.println("\nNotBoundException");
            System.out.println(nbe);
        } catch (java.lang.ArithmeticException ae) {
            System.out.println("\nArithmeticException");
            System.out.println(ae);
        }
        catch(Exception e){
            System.out.println("\nException");
            System.out.println(e);
        }
    }

    private static void printArgsFailed(){
        System.out.println("Invalid command line arguments. Run the program as follows:");
        System.out.println("java HRClient list <hostname>");
        System.out.println("java HRClient book <hostname> <type> <number> <name>");
        System.out.println("java HRClient guests <hostname>");
        System.out.println("java HRClient cancel <hostname> <type> <number> <name>");
        System.exit(1);
    }

    private static void listCall(HRInterface c) throws RemoteException {
        ArrayList<Room> rooms = c.list();
        // Print all rooms, their availability and price
        for (Room room : rooms)
            System.out.print(room);
    }

    private static void bookCall(String args[], HRInterface c) throws RemoteException {
    
        Scanner sc = new Scanner(System.in);
        // Parse the arguments accordingly
        int num = Integer.parseInt(args[3]);
        String type = args[2];
        String name = args[4];

        // Call the book method
        double price = c.book(type, num, name);

        // Booking successful 
        if (price > 0) {
            System.out.println("Booking successful\nTotal price: " + price);
        } 
        // No available rooms
        else if (price == 0) {
            // Print book error message and ask if user wants to register for callback
            printBookingFailed(c, type);
        } 
        // Available rooms but not as many as requested
        else {
            System.out.println("Available rooms, but less than requested...");
            int numRooms = 0;
            // Get room list
            ArrayList<Room> rooms = c.list();

            // Find the number of available rooms(numRooms) of the requested type
            for (Room room : rooms) {
                if (room.getType().equals(type)) {
                    numRooms = room.getNumber();
                    break;
                }
            }

            // Ask user if they want to book the available rooms
            System.out.print("Do you want to book " + numRooms + " rooms instead? (Y/N):");
            String answer = sc.next();

            // If they want then booking successful 
            if (answer.equalsIgnoreCase("Y")) {
                // Call book again but now with the available number of rooms
                price = c.book(type, numRooms, name);
                System.out.println("Booking successful\nTotal price: " + price);
            } else {
                // Print book arror message and ask if user wants to register for callback
                printBookingFailed(c, type);
            } 
        }
        sc.close();
    }

    private static void printBookingFailed(HRInterface c, String type) throws RemoteException{
        // Create a new HRClient object for registerCallback 
        HRClient client = new HRClient();
        Scanner sc = new Scanner(System.in);

        System.out.println("Booking failed...");
        System.out.print("Do you want to register for notifications when rooms of type " + type + " become available? (Y/N): ");
        String answer = sc.next();
        if (answer.equalsIgnoreCase("Y")) {
            waitFlag = false; //flag is set to false so main thread waits
            c.registerForCallback(client, type); // This client is registered for this type of room for callback
            System.out.println("Registered for a callback when rooms of type " + type + " become available.");
        }
        sc.close();
    }

    private static void guestsCall(HRInterface c) throws RemoteException {
        // Call the guests method
        ArrayList<Customer> customers = c.guests();
        //Print all customers and their reservations
        for (Customer customer : customers)
            System.out.print(customer);
        // Prints total number of customers
        System.out.println("Total: " + customers.size());
    }

    private static void cancelCall(String args[], HRInterface c) throws RemoteException{
        // Parse the arguments accordingly
        int num = Integer.parseInt(args[3]);
        String type = args[2];
        String name = args[4];

        // Call cancel method
        ArrayList<Room> rooms = c.cancel(type, num, name);
        // If list is empty the cancellation failed
        if (rooms.isEmpty()) {
            System.out.println("Cancellation failed...");
        } 
        // If list is not empty the cancellation was successful
        else {
            System.out.println("Cancellation successful!");
            // Get the customer list
            ArrayList<Customer> customers = c.guests();
            boolean hasRooms = false; //flag to see if customer still has reserved rooms

            // Check if customer still has reserved rooms
            for (Customer customer : customers) {
                if (customer.getName().equals(name) && customer.getType().equals(type) && customer.getNum() > 0) {
                    System.out.println("Customer " + name + " has still " + customer.getNum() + " booked rooms of type " + type);
                    hasRooms = true;
                    break;
                }
            }

            // If customer has no more rooms of the type
            if (!hasRooms)  
                System.out.println("Customer " + name + " has no more bookings of type " + type);
            // Print the available room of type the customer cancelled
            System.out.println("Available rooms now of type " + type + ": ");
            for (Room room : rooms) {
                if(room.getType().equals(type))
                    System.out.print(room);   
            }      
        }
    }
}