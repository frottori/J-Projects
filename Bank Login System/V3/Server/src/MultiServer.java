// Server
import java.io.*;
import java.net.*;

public class MultiServer {
    public static void main(String[] args){
        ServerSocket serverSock = null;
        int port = 1761;
        try{
            serverSock = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while(true){
                Socket clientSock = serverSock.accept();
                System.out.println("Client connected: " + clientSock.getInetAddress());

                Thread clientThread = new ClientHandlerThread(clientSock);
                clientThread.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(serverSock != null){
                    serverSock.close();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}

class ClientHandlerThread extends Thread{
    private Socket clientSock;

    public ClientHandlerThread(Socket clientSock){
        this.clientSock = clientSock;
    }
    @Override
    public void run(){
        try {
            UserAccounts info = new UserAccounts(); // Create an instance to access user account data
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

            int code = Integer.parseInt(in.readLine());

            //Get Login Info
            if(code == 1){
                ObjectOutputStream out = new ObjectOutputStream(clientSock.getOutputStream());

                // Send user account information to the client
                out.writeObject(info.getLoginInfo());
                out.writeObject(info.getMoney());
                out.writeObject(info.getGender());
                out.writeObject(info.getFirstName());
                out.writeObject(info.getLastName());
                out.close();
            }
            //Insert DB
            else if (code == 2){
                String uname = in.readLine();
                String passwd = in.readLine();
                String fname = in.readLine();
                String lname = in.readLine();
                String gder = in.readLine();
                info.insertDB(uname, passwd, fname, lname, gder);
            }
            // Update DB
            else if (code == 3){
                String uname = in.readLine();
                float balance = Float.parseFloat(in.readLine());
                info.updateDB(uname,balance);
            }   

            in.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        finally {
            try {
                clientSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}