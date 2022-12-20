import java.io.*;
import java.net.*;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean online = true;

    @Override
    public void run(){
        try {
            client = new Socket(InetAddress.getLocalHost(), 5000);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
            }
        } catch (IOException e){
        disconnect();
        }
    }
    public void disconnect(){
        online = false;
        try {
            in.close();
            out.close();
            if (!client.isClosed()){
                client.close();
            }
        } catch (IOException e){
            //ignore
        }
    }
    class InputHandler implements Runnable{

        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (online){
                    String message = inReader.readLine();
                    if (message.equals("/quit")){
                        out.println(message);
                        inReader.close();
                        disconnect();
                    } else{
                        out.println(message);
                    }
                }
            }catch (IOException e){
                disconnect();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}