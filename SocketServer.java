import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    public static  ArrayList<ServerRunnable> processes = new ArrayList<>();
    //set unique id
    public static int count = 1;

    //set port numbers
    int port = 42210;

    ServerSocket serverSocket = null;

    //use executor service to create a fixed thread pool to allow for concurrency
    public static ExecutorService pool = Executors.newFixedThreadPool(3);

    public static boolean flag = false;

    public void runServer() throws IOException {
        try {
            //create a socket and store the connection
            serverSocket = new ServerSocket(port);


        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true)
        {
            //accept socket connection
            Socket clientSocket = serverSocket.accept();

            //create an instance of the Server runnable class
            ServerRunnable process = new ServerRunnable(clientSocket);


            System.out.println("Process " + count + " was created");


            processes.add(process);
            count++;
            pool.execute(process);






        }

    }

   /*
    private static void tryReConnect()
    {
        try
        {
            ServerSocket.close();
            //empty my old lost connection and let it get by garbage col. immediately
            clientSocket=null;
            System.gc();
            //Wait a new client Socket connection and address this to my local variable
            clientSocket= ServerSocket.accept(); // Waiting for another Connection
            System.out.println("Connection established...");
        }catch (Exception e) {
            String message="ReConnect not successful "+e.getMessage();
            logit();//etc...
        }
    }

    */

}
