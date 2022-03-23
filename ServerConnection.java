import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.exit;


public class ServerConnection implements Runnable {
    public Socket server;
    BufferedReader in;
    PrintWriter out;


    public ServerConnection(Socket s) throws IOException {
        this.server = s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        //create avenue to send message to the client
        out = new PrintWriter(s.getOutputStream(), true);
    }


    String val = "";
    String Receive() throws Exception
    {
        try {
            val = in.readLine();

        }catch(Exception e)
        {
            val = null;
            e.printStackTrace();
        }
        return val;
    }

    @Override
    public void run() {


        ServerRunnable value = new ServerRunnable(server);

        //output the message gotten from the server
        try {
            while (true) {
                val = Receive();

                if (val == null)
                {
                    System.out.print("User left!");
                    System.exit(0);

                }

                String line[] =val.split(" ");
                if(line[2].equalsIgnoreCase("Goodbye"))
                {
                    System.out.print("User left!");
                    break;
                    //System.exit(0);
                }


                else {
                    System.out.println(val);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

