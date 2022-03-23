import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//Client Process 1.


public class client {
    public static void main(String[] args) throws IOException {

        //define variables
        String localhost = "127.0.0.1"; //args[0];
        int portNumber = 42210;
        Socket clientSocket;


        //if server is down, wait till server is up before establishing a connection
        while(true) {
            try {
                clientSocket = new Socket(localhost, portNumber);

                break; // We connected! Exit the loop.
            } catch(IOException e) {
                // connection failed, wait.
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("Connecting to server...");
                } catch(InterruptedException ie) {
                    // Interrupted.
                }
            }
        }

        //create a server connection
        ServerConnection ServerConn = new ServerConnection(clientSocket);
        Scanner type = new Scanner(System.in);

        //start the thread listening for messages
       new Thread(ServerConn).start();

            try {

                while (true) {
                    //ask the user for their command
                    System.out.print("What is your command ? \n" +
                            ">");

                    //save the command
                    String msg = type.nextLine();

                    //create IO stream
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                        //send message to the server
                        out.println(msg);
                        out.println(args[1]);

                    }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

        }



