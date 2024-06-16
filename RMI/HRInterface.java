import java.util.ArrayList;
import java.rmi.*;

public interface HRInterface extends Remote {

    public ArrayList<Room> list() 
    throws RemoteException;

    public double book(String type, int num, String name) 
    throws RemoteException;

    public ArrayList<Customer> guests() 
    throws RemoteException;

    public ArrayList<Room> cancel(String type, int num, String name) 
    throws RemoteException;

    public void registerForCallback (HRClientInterface callbackObj, String type) 
    throws RemoteException;
}