import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server implements IServer {
    private ServerSocket serverSocket;
    private UserDatabase userDatabase;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        userDatabase = new UserDatabase(new ArrayList<>()); // Assuming your UserDatabase has a constructor that takes a list of Users
        loadUsers();  // Method to load users from a file or initialize database
    }

    public void loadUsers() {
        File file = new File("user_data.dat");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                User user;
                while ((user = (User) ois.readObject()) != null) {
                    userDatabase.signUp(user);
                }
            } catch (EOFException ignored) {
                // End of file reached
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("Server started, waiting for connections...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, userDatabase).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(1112);
            server.start();
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
