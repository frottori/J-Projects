import java.rmi.*;
import java.rmi.registry.*;

public class HRServer {
    public HRServer(){
		try {
			HRInterface c = new HRImpl();
			// Bind this object instance to the name "HR"
	 		Naming.rebind("//localhost/HR", c);
	 		System.out.println("HR bound in registry");
	  	} catch (Exception e) {
			System.out.println("Server exception: " + e);
	  }
	}

	public static void main(String args[]) {

		System.out.println("RMI server started");
		try { 
			//special exception handler for registry creation
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created.");
		} 
		catch (RemoteException e) {
			//do nothing, error means registry already exists
			System.out.println("java RMI registry already exists.");
		}
		//Instantiate RmiServer
		new HRServer();
	}
}