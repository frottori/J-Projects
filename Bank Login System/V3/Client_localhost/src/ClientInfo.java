import java.io.*;
import java.net.*;
import java.util.HashMap;

public class ClientInfo {
    private HashMap <String,String> loginInfo = new HashMap<String,String>();
    private HashMap <String,Double> money = new HashMap<String,Double>();
    private HashMap <String,String> gender = new HashMap<String,String>();
    private HashMap <String,String> firstName = new HashMap<String,String>();
    private HashMap <String,String> lastName = new HashMap<String,String>();
    String serverAddress = "localhost"; // Server's IP address
    int serverPort = 1761; // Server's port number

    public ClientInfo(){
  

        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);
            // I write to server what i want
            // Code = 1 : Constructor
            
            PrintWriter pr = new PrintWriter (clientSocket.getOutputStream(),true);
            pr.println(Integer.toString(1));

            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // Receive user account information
            loginInfo = (HashMap<String, String>) in.readObject();
            money = (HashMap<String, Double>) in.readObject();
            gender = (HashMap<String, String>) in.readObject();
            firstName = (HashMap<String, String>) in.readObject();
            lastName = (HashMap<String, String>) in.readObject();

            pr.close();
            in.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertDB(String uname,String passwd,String fname,String lname,String gder){

        try {
            // Code = 2 : Insert DB 
            Socket clientSocket = new Socket(serverAddress, serverPort);
            PrintWriter pr = new PrintWriter (clientSocket.getOutputStream(),true);
            pr.println(Integer.toString(2));
            pr.println(uname);
            pr.println(passwd);
            pr.println(fname);
            pr.println(lname);
            pr.println(gder);

            pr.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDB(String uname,float balance){

            try {
            // Code = 3 : Update
            Socket clientSocket = new Socket(serverAddress, serverPort);
            PrintWriter pr = new PrintWriter (clientSocket.getOutputStream(),true);
            pr.println(Integer.toString(3));
            pr.println(uname);
            pr.println(balance);  

            pr.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public HashMap<String,String> getLoginInfo(){
        return loginInfo;
    }

    public HashMap<String,Double> getMoney(){
        return money;
    }

    public HashMap<String,String> getGender(){
        return gender;
    }

    public HashMap<String,String> getFirstName(){
        return firstName;
    }

    public HashMap<String,String> getLastName(){
        return lastName;
    }
}