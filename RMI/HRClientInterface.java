import java.rmi.*;

public interface HRClientInterface extends Remote {
    void notifyAvailability(String type) throws RemoteException;
} 