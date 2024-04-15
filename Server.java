import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.15
 * The Server class represents a server application that manages client connections
 * and user data in a social media system.
 * This is for phase 2.
 */

public class Server {
    private ServerSocket serverSocket;
    private UserDatabase userDatabase;
    private static final String DATA_FILE = "server_data.dat";

    public Server(int port) throws IOException {
        serverSocket = createServerSocket(port);
        userDatabase = loadOrCreateDatabase();
    }

    protected ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }

    protected UserDatabase loadOrCreateDatabase() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists() && !file.isDirectory()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    return (UserDatabase) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load database: " + e.getMessage());
            e.printStackTrace();
        }
        return new UserDatabase(new ArrayList<>());
    }

    protected void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(userDatabase);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
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
