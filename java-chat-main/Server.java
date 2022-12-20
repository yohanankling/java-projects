/* the base for server and client code learned from:
https://www.youtube.com/watch?v=hIc_9Wbn704 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
public class Server implements Runnable {
   private ArrayList<clients> users;
   private ServerSocket server;
   private boolean online;
   private ExecutorService pool;

   public Server(){
       users = new ArrayList<>();
       online = true;
   }

    @Override
    public void run(){
        try {
            server = new ServerSocket(5000);
            pool = Executors.newCachedThreadPool();
            while (online) {
                Socket client = server.accept();
                clients newConnection = new clients(client);
                users.add(newConnection);
                pool.execute(newConnection);
            }
        } catch (IOException e) {
            shutdown();
        }
    }
    public void broadcast(String message) {
        for (clients client : users) {
            if (client != null) {
                client.sendMessages(message);
            }
        }
    }

    public void chatWith(clients client , String message){
        client.sendMessages(message);
    }

    public void shutdown() {
        try {
            broadcast("we are closing the server, have a nice day!");
            online = false;
            pool.shutdown();
            if (!server.isClosed()) {
                server.close();
            }
            for (clients client : users) {
                client.disconnect();
            }
        } catch (IOException e) {
            // ignore
        }
    }

    class clients implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public clients(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("enter your name: ");
                name = in.readLine();
                chackName(this);
                System.out.println(name + " is connected");
                broadcast(name + " is connected");
                String incomingMessage;
                while ((incomingMessage = in.readLine()) != null) {
                    boolean validCommand = false;
                    if (incomingMessage.contains(" ")){
                    String[] parts = incomingMessage.split(" ", 2);
                    String command = parts[0];
                    incomingMessage = parts[1];
                        if ((command.equals("/broadcast"))) {
                             validCommand = true;
                             broadcast(name + "-> " + incomingMessage);
                         }
                        for (clients ch : users) {
                                 if (ch.name.equals(command)) {
                                     validCommand = true;
                                     if (ch.client.isClosed()){
                                         broadcast(command +" is disconnected..");
                                         users.remove(ch);
                                         break;
                                     }
                                     chatWith(ch,name +"-> "+ incomingMessage);
                                     break;
                                 }
                            }
                    }
                    if ((incomingMessage.equals("/quit"))){
                        validCommand = true;
                        broadcast(name + " disconnected from the chat" );
                        disconnect();
                    } else if((incomingMessage.equals("/online"))) {
                        validCommand = true;
                        for (clients ch : users) {
                            chatWith(this, ch.name);
                        }
                    }
                    if (!validCommand) {
                         chatWith(this ,name +", you missed the format for sending messages..");
                     }
                }
            } catch (IOException e) {
                disconnect();
            }
        }

        public void sendMessages(String message) {
            out.println(message);
        }
        public void kick(clients ch){
            try {
                ch.in.close();
                ch.out.close();
                if (!client.isClosed()) {
                    client.close();
                }
                users.remove(ch);
                broadcast(ch.name +" is disconnected..");
            } catch (IOException e) {
                // ignore
            }
        }

        public void chackName(clients ch){
            int namesCounter = 0;
            for (clients client : users){
                if (client.client.isClosed()){
                    users.remove(client);
                }
                if (client.name.equals(ch.name)){
                    namesCounter++;
                }
            }
            if (namesCounter > 1){
                changeName(this);
            }
        }


            public void changeName(clients ch){
                chatWith(ch, "this name is already taken..try again!");
                try {
                    name = ch.in.readLine();
                    chackName(ch);
                }catch (IOException e){
                    //ignore
                }

        }
        public void disconnect() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
                users.remove(this);
            } catch (IOException e) {
                // ignore
            }
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
