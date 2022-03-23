import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) throws IOException {

        //Start the server
        System.out.println("Main server!");
        SocketServer s = new SocketServer();
        s.runServer();


    }
}
